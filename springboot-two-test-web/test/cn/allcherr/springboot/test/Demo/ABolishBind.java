package cn.allcherr.springboot.test.Demo;

import cn.allcheer.springbootbylihui.springboottwotestweb.SpringbootTwoTestWebApplication;
import cn.allcheer.springbootbylihui.utils.baofoo.rsa.RsaCodingUtil;
import cn.allcheer.springbootbylihui.utils.baofoo.rsa.SignatureUtils;
import cn.allcheer.springbootbylihui.utils.baofoo.util.FormatUtil;
import cn.allcheer.springbootbylihui.utils.baofoo.util.HttpUtil;
import cn.allcheer.springbootbylihui.utils.baofoo.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 解绑接口
 * Company: www.baofu.com
 * @author dasheng(大圣)2
 * @date 2018年3月2日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTwoTestWebApplication.class)
@Slf4j
public class ABolishBind{
	@Test
	public static void abolishBind() {
		
		String send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//报文发送日期时间		
		String  pfxpath ="D:\\CER_EN_DECODE\\AgreementPay\\bfkey_100025773@@200001173.pfx";//商户私钥        
        String  cerpath = "D:\\CER_EN_DECODE\\AgreementPay\\bfkey_100025773@@200001173.cer";//宝付公钥
   
        String AesKey = "4f66405c4f66405c";//商户自定义(可随机生成  AES key长度为=16位)
		String dgtl_envlp = "01|"+AesKey;//使用接收方的公钥加密后的对称密钥，并做Base64转码，明文01|对称密钥，01代表AES[密码商户自定义]
		log.info("密码dgtl_envlp："+dgtl_envlp);
		try {
			dgtl_envlp = RsaCodingUtil.encryptByPubCerFile(SecurityUtil.Base64Encode(dgtl_envlp), cerpath);//公钥加密
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String ProtocolNo = "2018012510553720000117300110";//签约协议号（确认绑卡返回）
		log.info("签约协议号："+ProtocolNo);
		try {
			ProtocolNo = SecurityUtil.AesEncrypt(SecurityUtil.Base64Encode(ProtocolNo), AesKey);//先BASE64后进行AES加密
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("签约协议号AES结果:"+ProtocolNo);

		Map<String,String> DateArry = new TreeMap<String,String>();
		DateArry.put("send_time", send_time);
		DateArry.put("msg_id", "TISN"+System.currentTimeMillis());//报文流水号
		DateArry.put("version", "4.0.0.0");
		DateArry.put("terminal_id", "200001173");
		DateArry.put("txn_type", "04");//交易类型
		DateArry.put("member_id", "100025773");
		DateArry.put("dgtl_envlp", dgtl_envlp);
		DateArry.put("user_id", "000000");//用户在商户平台唯一ID (和绑卡时要一致)
		DateArry.put("protocol_no", ProtocolNo);//签约协议号（密文）
		
		String SignVStr = FormatUtil.coverMap2String(DateArry);
		log.info("SHA-1摘要字串："+SignVStr);
		String signature = null;//签名
		try {
			signature = SecurityUtil.sha1X16(SignVStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("SHA-1摘要结果："+signature);
		String Sign = null;
		try {
			Sign = SignatureUtils.encryptByRSA(signature, pfxpath, "100025773_286941");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("RSA签名结果："+Sign);
		DateArry.put("signature", Sign);//签名域
		
		String PostString  = HttpUtil.RequestForm("https://vgw.baofoo.com/cutpayment/protocol/backTransRequest", DateArry);
		log.info("请求返回:"+PostString);

		Map<String, String> ReturnData = null;
		try {
			ReturnData = FormatUtil.getParm(PostString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(!ReturnData.containsKey("signature")){
			try {
				throw new Exception("缺少验签参数！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String RSign=ReturnData.get("signature");
		log.info("返回的验签值："+RSign);
		ReturnData.remove("signature");//需要删除签名字段		
		String RSignVStr = FormatUtil.coverMap2String(ReturnData);
		log.info("返回SHA-1摘要字串："+RSignVStr);
		String RSignature = null;//签名
		try {
			RSignature = SecurityUtil.sha1X16(RSignVStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("返回SHA-1摘要结果："+RSignature);

		try {
			if(SignatureUtils.verifySignature(cerpath,RSignature,RSign)){
                log.info("Yes");//验签成功
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!ReturnData.containsKey("resp_code")){
			try {
				throw new Exception("缺少resp_code参数！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(ReturnData.get("resp_code").toString().equals("S")){
			log.info("解绑成功！");
		}else if(ReturnData.get("resp_code").toString().equals("F")){
			log.info("解绑失败！");
		}
	}
}

