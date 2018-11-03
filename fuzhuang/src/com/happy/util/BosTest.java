package com.happy.util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.BosObject;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter;

public class BosTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            String ACCESS_KEY_ID = "8c2d2157d6cc493984188b74c1d3e8bb";   
            String SECRET_ACCESS_KEY = "55695312af334122babf853052b566d3";
            String bucketName = "leng20160223"; //请选用一个唯一的 bucket名称，否则create_bucket会因为重名报错
            String targetKey = "sampleKey.txt"; 

            //创建BOS client
            BosClientConfiguration config = new BosClientConfiguration();
            config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
            BosClient client = new BosClient(config);

            //创建bucket
            client.createBucket(bucketName);

            //上传内容到BOS
            InputStream inputStream = new ByteArrayInputStream("hello world".getBytes());;
            PutObjectResponse putObjectResponseFromInputStream = client.putObject(bucketName, targetKey, inputStream);
            //System.out.println(putObjectResponseFromInputStream.getETag());

            //从BOS中下载内容
            BosObject object = client.getObject(bucketName, targetKey);
            InputStream objectContent = object.getObjectContent();
            BufferedReader in = new BufferedReader(new InputStreamReader(objectContent));
            String line = "";
            PrintWriter out = resp.getWriter();
            while ((line = in.readLine()) != null){
                out.print(line);
            }

        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
    public static void main(String[] args) {
    	String ACCESS_KEY_ID = "eb5db4c518604c3aa4dd84736349f80d";   
        String SECRET_ACCESS_KEY = "65cec5fad1c040c28ebc4efd683d1c83";
        String bucketName = "qt20160223"; //请选用一个唯一的 bucket名称，否则create_bucket会因为重名报错
        String targetKey = "sampleKeys.txt"; 

        //创建BOS client
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        BosClient client = new BosClient(config);

        //创建bucket
        client.createBucket(bucketName);

        //上传内容到BOS
        InputStream inputStream = new ByteArrayInputStream("hello world".getBytes());;
        PutObjectResponse putObjectResponseFromInputStream = client.putObject(bucketName, targetKey, inputStream);
        //System.out.println(putObjectResponseFromInputStream.getETag());
    }
}
