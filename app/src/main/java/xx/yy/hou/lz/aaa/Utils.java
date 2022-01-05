package xx.yy.hou.lz.aaa;

import static xx.yy.hou.lz.util.TimeUtilKt.debug;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import xx.yy.hou.lz.util.SerializeUtils;

public class Utils {

  public static void fa(String user, String password, String x) throws Exception {
    sendObject(user, password, x);
  }

  public static String jie(String user, String password) throws Exception {
    return getObject(user, password);
  }

  public static Boolean check(String user, String password) throws Exception {
    String result = checkLogin(user, password);
    return !result.equals("n");
  }


  public static String sendObject(String user, String password, Object x) throws Exception {
    URL postUrl = new URL("http://10.0.2.2:8080/save"); // Post请求的url，与get不同的是不需要带参数

    HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); // 打开连接

    // 设置是否向connection输出，因为这个是post请求，参数要放在
    connection.setDoOutput(true); // http正文内，因此需要设为true
    connection.setDoInput(true); // Read from the connection. Default is true.
    connection.setRequestMethod("POST"); // 默认是 GET方式
    connection.setUseCaches(false); // Post 请求不能使用缓存
    connection.setInstanceFollowRedirects(true);

    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
    // 要注意的是connection.getOutputStream会隐含的进行connect。
    connection.connect();

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    // The URL-encoded contend
    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
    String content = "obj=" + URLEncoder.encode(SerializeUtils.serialize(x), "UTF-8"); // obj =
    content += "&user=" + user; // user=
    content += "&password=" + password; // password =
    out.writeBytes(content); // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.flush();
    out.close();

    // 以下是读取返回数据
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder msg = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      msg.append(line);
    }
    reader.close();
    connection.disconnect();

    debug("fan hui shu ju send :: " + msg.toString());

    return msg.toString();
  }

  public static String checkLogin(String user, String password) throws Exception {
    URL postUrl = new URL("http://10.0.2.2:8080/check"); // Post请求的url，与get不同的是不需要带参数

    HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); // 打开连接

    // 设置是否向connection输出，因为这个是post请求，参数要放在
    connection.setDoOutput(true); // http正文内，因此需要设为true
    connection.setDoInput(true); // Read from the connection. Default is true.
    connection.setRequestMethod("POST"); // 默认是 GET方式
    connection.setUseCaches(false); // Post 请求不能使用缓存
    connection.setInstanceFollowRedirects(true);

    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
    // 要注意的是connection.getOutputStream会隐含的进行connect。
    connection.connect();

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    // The URL-encoded contend
    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
    String content = "";
    content += "user=" + user; // user=
    content += "&password=" + password; // password =
    out.writeBytes(content); // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.flush();
    out.close();

    // 以下是读取返回数据
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder msg = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      msg.append(line);
    }
    reader.close();
    connection.disconnect();

    debug("fan hui shu ju send :: " + msg.toString());

    return msg.toString();
  }
//  public static String sendObject(String path, String name, Object x) throws Exception {
//    URL postUrl = new URL("http://10.0.2.2:8080/" + path); // Post请求的url，与get不同的是不需要带参数
//
//    HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); // 打开连接
//
//    // 设置是否向connection输出，因为这个是post请求，参数要放在
//    connection.setDoOutput(true); // http正文内，因此需要设为true
//    connection.setDoInput(true); // Read from the connection. Default is true.
//    connection.setRequestMethod("POST"); // 默认是 GET方式
//    connection.setUseCaches(false); // Post 请求不能使用缓存
//    connection.setInstanceFollowRedirects(true);
//
//    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
//    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
//    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
//    // 要注意的是connection.getOutputStream会隐含的进行connect。
//    connection.connect();
//
//    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//    // The URL-encoded contend
//    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
//    String content = name + "=" + URLEncoder.encode(SerializeUtils.serialize(x), "UTF-8");
//    out.writeBytes(content); // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
//    out.flush();
//    out.close();
//
//    // 以下是读取返回数据
//    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//    StringBuilder msg = new StringBuilder();
//    String line;
//    while ((line = reader.readLine()) != null) {
//      Log.e("line: ", line);
//      msg.append(line);
//    }
//    reader.close();
//    connection.disconnect();
//
//    return msg.toString();
//  }

  public static String getObject(String user, String password) throws Exception {
    URL postUrl = new URL("http://10.0.2.2:8080/gett"); // Post请求的url，与get不同的是不需要带参数

    HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); // 打开连接

    // 设置是否向connection输出，因为这个是post请求，参数要放在
    connection.setDoOutput(true); // http正文内，因此需要设为true
    connection.setDoInput(true); // Read from the connection. Default is true.
    connection.setRequestMethod("POST"); // 默认是 GET方式
    connection.setUseCaches(false); // Post 请求不能使用缓存
    connection.setInstanceFollowRedirects(true);

    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
    // 要注意的是connection.getOutputStream会隐含的进行connect。
    connection.connect();

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    // The URL-encoded contend
    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
    String content = "";
    content += "user=" + user; // user=
    content += "&password=" + password; // password =
    out.writeBytes(content); // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.flush();
    out.close();

    // 以下是读取返回数据
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder msg = new StringBuilder();
    int line;
    while ((line = reader.read()) != -1) {
      msg.append((char) line);
    }
    reader.close();
    connection.disconnect();

    debug("fan hui shu ju: get" + msg.toString());
    return msg.toString();
  }

  public static String sendAuto(String path, Object x) throws Exception {
    URL postUrl = new URL("http://10.0.2.2:8080/" + path); // Post请求的url，与get不同的是不需要带参数

    HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection(); // 打开连接

    // 设置是否向connection输出，因为这个是post请求，参数要放在
    connection.setDoOutput(true); // http正文内，因此需要设为true
    connection.setDoInput(true); // Read from the connection. Default is true.
    connection.setRequestMethod("POST"); // 默认是 GET方式
    connection.setUseCaches(false); // Post 请求不能使用缓存
    connection.setInstanceFollowRedirects(true);

    // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
    // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
    // 要注意的是connection.getOutputStream会隐含的进行connect。
    connection.connect();

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    // The URL-encoded contend
    // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致

    String content = "o=" + URLEncoder.encode(SerializeUtils.serialize(x), "UTF-8");
    content += "&className=" + x.getClass().getName();
    Log.e("asdf", x.getClass().getName());
    Log.e("asdf", content);

    out.writeBytes(content); // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
    out.flush();
    out.close();

    // 以下是读取返回数据
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder msg = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      Log.e("line: ", line);
      msg.append(line);
    }
    reader.close();
    connection.disconnect();

    return msg.toString();
  }
}
