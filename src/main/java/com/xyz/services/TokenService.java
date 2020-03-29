package com.xyz.services;

import com.xyz.common.Utils;
import com.xyz.mapper.TokenMapper;
import com.xyz.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author lzs
* @Date 2020/3/21
*/

@Service
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;

    //基础配置
    public static final int TOKEN_EXPIRE_TIME = 100000000; // 自动登出时间
    public static final boolean CHECK_EXPIRE = false;

    public String createToken(long userId, int client, String ip) {
        Token currentToken = tokenMapper.getToken(userId, client);
        String newToken = Utils.createUUID();
        long currentTime = Utils.createTimestamp();
        if(currentToken != null) {
            currentToken.setUpdateAt(currentTime);
            currentToken.setExpiresAt(currentTime + TOKEN_EXPIRE_TIME);
            currentToken.setToken(newToken);
            currentToken.setIp(ip);
            tokenMapper.updateToken(currentToken);
        } else{
            Token token = new Token();
            token.setToken(newToken);
            token.setUserId(userId);
            token.setClient(client);
            token.setIp(ip);
            token.setCreatedAt(currentTime);
            token.setUpdateAt(currentTime);
            token.setExpiresAt(currentTime + TOKEN_EXPIRE_TIME);

            tokenMapper.createToken(token);
        }
        return newToken;
    }

    public long checkToken(String token) {
        Token oldToken = tokenMapper.getTokenByStr(token);
        if(oldToken == null) {
            return -1;
        }
        if(CHECK_EXPIRE) {
            long currentTime = Utils.createTimestamp();
            if(oldToken.getExpiresAt() < currentTime ) {
                return -1;
            }
        }
        return oldToken.getUserId();
    }

    public boolean deleteToken(String token) {
        return tokenMapper.deleteToken(token) == 1;
    }
}
