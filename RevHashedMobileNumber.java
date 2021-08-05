package in.linus.csam;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RevHashedMobileNumber {
    public static String phnNumber = "";
    public static void main(String[] args) {
        int[] a = new int[]{9,9,0,0,2,2};
        int[] b = new int[]{1,3,4,5,6,7,8};
        int[] c = new int[]{9,8,7};
        System.out.println("Found: "+a.length);
        while(true){
            String str = generateMobileNumber(a,b,c);
            System.out.println("==> Checking: "+str);
            //c83f6785bb18b445157a87e825b85dca
            if(calculateMD5(str).equals("36ed2026a5de99113492139be527f7a0")){ // "36ed2026a5de99113492139be527f7a0"
                System.out.println("=================Found: "+str);
                break;
            };
        }
    }

    private static String generateMobileNumber(int[] a, int[] b, int[] c) {

        int[] randNum = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        randNum[0] = 8; //c[generateRandom(1)];
        int i = 0;
        while (i < a.length){
            boolean val = true;
            while (val) {
                int index = generateRandom(1, 9);
                if (randNum[index] == -1) {
                    randNum[index] = a[i];
                    val = false;
                }
            }
            i = i + 1;

        }

        for(int v1=0;v1 < randNum.length;v1++){
            if(randNum[v1] == -1){
                randNum[v1] = b[generateRandom(0, b.length -1)];
            }
        }
        StringBuilder phnNumber = new StringBuilder();
        for(int v1=0;v1 < randNum.length;v1++){
            phnNumber.append(randNum[v1]);
        }

        return phnNumber.toString();
    }

    private static String calculateMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static int generateRandom(int min, int max){
        int rd = 0;
        rd = (int) (Math.random() * (max - min + 1) + min);
        return rd;

    }
}
