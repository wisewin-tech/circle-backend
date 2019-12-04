package com.wisewin.backend.util.hx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wisewin.backend.util.hx.entity.HXUser;
import com.wisewin.backend.util.hx.entity.Token;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class  HXUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String ORG_NAME = "1118190605042635";//企业的唯一标识，开发者在环信开发者管理后台注册账号时填写的企业 ID
    private static final String CLIENT_ID = "YXA67v2iSodKEemG4snZ5xZAvQ";//App的client_id
    private static final String CLIENT_SECRET = "YXA6VKd2m9H3qnz2DhIhzzhWn5isL8Y";//App的client_secret
    private static final String APP_NAME = "quanquan";//	同一“企业”下“APP”唯一标识，开发者在环信开发者管理后台创建应用时填写的“应用名称”
    private static final String URL_PREFIX = "http://a1.easemob.com/" + ORG_NAME + "/" + APP_NAME + "/";//链接前缀

    public enum HXMessageType {
        //文本
        txt,
        img,//图片
        loc,//位置
        audio,//音频
        video,//视频
        file//文件
    }

    public static Token getToken() {
        try {
            JSONObject body = new JSONObject();
            body.put("grant_type", "client_credentials");
            body.put("client_id", CLIENT_ID);
            body.put("client_secret", CLIENT_SECRET);
            HttpEntity httpEntity = new HttpEntity(body.toString(), null);
            ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity(URL_PREFIX + "token", httpEntity, Token.class);
            return tokenResponseEntity.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加用户
     *
     * @param username 用户名称（手机号唯一非空）
     * @param password 密码
     * @return 是否成功
     */
    public static boolean addUser(String username, String password) {
        try {
            JSONArray body = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            body.add(jsonObject);
            HttpEntity httpEntity = new HttpEntity(body.toString(), null);
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users", httpEntity, null);
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户密码
     *
     * @param username    用户名
     * @param newpassword 新密码
     * @return 是否成功
     */
    public static boolean updatePassword(String username, String newpassword) {
        try {
            JSONObject body = new JSONObject();
            body.put("newpassword", newpassword);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + getToken().getAccess_token());
            HttpEntity httpEntity = new HttpEntity(body.toString(), headers);
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users/{username}/password", httpEntity, null, username);
            System.out.println(responseEntity.getStatusCode());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户
     *
     * @param username 用户名
     */
    public static boolean deleteUser(String username) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
            ResponseEntity<HXUser> responseEntity = restTemplate.exchange(URL_PREFIX + "users/{username}", HttpMethod.DELETE, httpEntity, HXUser.class, username);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加好友
     *
     * @param ownerUsername 用户名
     * @param friendName    好友用户名
     * @return 是否成功
     */
    public static boolean addFriend(String ownerUsername, String friendName) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "users/{owner_username}/contacts/users/{friend_username}", httpEntity, HXUser.class, ownerUsername, friendName);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除好友
     *
     * @param ownerUsername 用户名
     * @param friendName    好友用户名
     * @return 是否成功
     */
    public static boolean deleteFriend(String ownerUsername, String friendName) {
        try {
            HttpEntity httpEntity = new HttpEntity(null, getHttpHeaders(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
            ResponseEntity responseEntity = restTemplate.exchange(URL_PREFIX + "users/{owner_username}/contacts/users/{friend_username}", HttpMethod.DELETE, httpEntity, HXUser.class, ownerUsername, friendName);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送消息
     *
     * @param sendUser   发送用户
     * @param targetUser 接收用户
     * @param msg        发送消息
     * @return 是否成功
     */
    public static boolean sendToUser(String sendUser, String targetUser, String msg) {
        try {
            JSONObject body = new JSONObject();
            body.put("target_type", "users");
            JSONArray targetUserjson = new JSONArray();
            targetUserjson.add(targetUser);
            body.put("target", targetUserjson);
            JSONObject msgJson = new JSONObject();
            msgJson.put("type", HXMessageType.txt.name());
            msgJson.put("msg", msg);
            body.put("msg", msgJson);
            body.put("from", sendUser);
            HttpEntity httpEntity = new HttpEntity(body, getHttpHeaders(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON));
            ResponseEntity responseEntity = restTemplate.postForEntity(URL_PREFIX + "messages", httpEntity, null);
            System.out.println(responseEntity.getStatusCode().value());
            return responseEntity.getStatusCode().value() == 200;
        } catch (RestClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取HttpHeaders
     *
     * @param contentType 客户端发送类型
     * @param accept      响应类型
     * @return HttpHeaders
     */
    private static HttpHeaders getHttpHeaders(MediaType contentType, MediaType... accept) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getToken().getAccess_token());
        headers.setContentType(contentType != null ? contentType : MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList((accept != null && accept.length > 0) ? accept : new MediaType[]{MediaType.APPLICATION_JSON}));
        return headers;
    }

}
