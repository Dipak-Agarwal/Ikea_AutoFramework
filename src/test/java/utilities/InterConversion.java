package utilities;

import java.util.Base64;

public class InterConversion {

    public String encode(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decode(String encodedString){
        return new String(Base64.getDecoder().decode(encodedString));
    }

    public String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }
        return hex.toString();
    }

//    public static void main(String[] args){
//        ProcessHandle.allProcesses().filter(process -> process.info().command().stream().anyMatch(s -> s.contains("chromedriver") || s.contains("geckodriver"))).forEach(process -> process.destroyForcibly());
//    }
}
