package com.example.demo.utils;

import com.example.demo.comment.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-08-03 10:42
 **/
public class JwtUtil {
    //生成token字符串的方法
    public static String getJwtToken(String id, String nickname){
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")	//头信息
                .setSubject("demo")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .claim("id", id)  //设置token主体部分 ，存储用户信息
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, Constant.APP_SECRET)
                .compact();

        return JwtToken;
    }

    public static void main(String[] args) {
        String hyx = getJwtToken("1", "hyx");
        System.out.println(hyx);
        Claims body = Jwts.parser().setSigningKey(Constant.APP_SECRET).parseClaimsJws(hyx).getBody();
        System.out.println(body.get("id")+" "+body.get("nickname"));
        System.out.println(body.getIssuedAt()+"");
        System.out.println(body.getExpiration()+"");
    }
}