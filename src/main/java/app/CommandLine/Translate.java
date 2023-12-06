package app.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {

    public static void main(String[] args) throws IOException {
        String text = "simp";
        //Translated text: Hallo Welt!
        System.out.println("Translated text: " + translate("", "vi", text));
    }

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        // // Tạo URL cho yêu cầu dịch
        String urlStr = "https://script.google.com/macros/s/AKfycbzt3DKEZ95x28Vhi7kVoUKhTjymbnNtOy8q6Q67rkAsddozh5ab2Vql7_4V14ZbnBRG8g/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr); // Tạo đối tượng URL từ địa chỉ URL đã tạo
        StringBuilder response = new StringBuilder(); // Chuỗi để lưu trữ phản hồi từ server
        // Mở kết nối HTTP
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); // Tạo kết nối HTTP từ URL
        con.setRequestProperty("User-Agent", "Mozilla/5.0"); // Thiết lập thuộc tính User-Agent để giả vờ là trình duyệt
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // Đọc dữ liệu từ kết nối
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine); // Thêm từng dòng vào chuỗi phản hồi
        }
        in.close();
        return response.toString(); // Trả về chuỗi phản hồi đã xây dựng từ server
    }
}