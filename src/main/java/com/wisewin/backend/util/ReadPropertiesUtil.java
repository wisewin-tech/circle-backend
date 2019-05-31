package com.wisewin.backend.util;




import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 *
 */
public class ReadPropertiesUtil {

    /**
     * 私有化Properties对象
     */
    private Properties prop;

    /**
     * 通过单例模式获取ReadPropertiesFile对象
     */
    private ReadPropertiesUtil(){}

    //静态内部类实例
    private static class SingleTonHoler{
        private static ReadPropertiesUtil INSTANCE = new ReadPropertiesUtil();
    }

    public static ReadPropertiesUtil getInstance(){
        return SingleTonHoler.INSTANCE;
    }


    /**
     * 读取文件的路径
     * @param url
     * @throws UnsupportedEncodingException
     */
    public void setPropertiesDataSource(String url) throws UnsupportedEncodingException {
        prop = new Properties();
//        InputStream in  = getClass().getResourceAsStream(url);
//        BufferedReader bf=new BufferedReader(new InputStreamReader(in));
        InputStream in = getClass().getClassLoader().getResourceAsStream(url);
        try {
            prop.load(in);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 通过key值获取文件的int类型数据
     * @param key
     * @return
     */
    public Integer getInteger(String key){
        return Integer.parseInt(prop.getProperty(key));
    }
    /**
     * 通过key值获取文件的String类型数据
     * @param key
     * @return
     */
    public String getString(String key){
        return prop.getProperty(key);
    }
    /**
     * 通过key值获取文件的double类型数据
     * @param key
     * @return
     */
    public Double getDouble(String key){
        return Double.parseDouble(prop.getProperty(key));
    }
    /**
     * 通过key值获取文件的boolean类型数据
     * @param key
     * @return
     */
    public Boolean getBoolean(String key){
        return Boolean.parseBoolean(prop.getProperty(key));
    }
//    public static void main(String[] a) throws IOException {
//        Properties properties = new Properties();
//        // 使用ClassLoader加载properties配置文件生成对应的输入流
//        InputStream in = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream
//                ("system_config.properties");
//        // 使用properties对象加载输入流
//        properties.load(in);
//        //获取key对应的value值
//        String bucketName = properties.getProperty("bucketName");
//        System.out.println(bucketName);
//        in.close();
//    }
}
