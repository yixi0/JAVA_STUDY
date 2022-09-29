import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Electricity {
    public static void main(String[] args) throws IOException {
        StringBuffer bs = new StringBuffer();
        URL sendUrl = new URL("http://192.168.84.3:9090/cgcSims/" +
                "selectList.do?beginTime=2022-09-23&endTime=2022-09-24&" +
                "type=2&client=192.168.84.110&roomId=8133&roomName=502");
        URLConnection connection = sendUrl.openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","application/json；chert=UTF-8");
        OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream(), "GBK");
        out.flush();
        out.close();
        connection.connect();
        InputStream is = connection.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "GBK"));

        String l;
        while ((l = buffer.readLine()) != null) {
            bs.append(l);
        }
        String str = bs.toString();
        String electricity = str.split("<td width=\"13%\" align=\"center\">")[3].split("</td>")[0].trim();
        System.out.println("electricity = " + electricity);
    }
}
