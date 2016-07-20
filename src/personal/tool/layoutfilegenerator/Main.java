package personal.tool.layoutfilegenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result="";
		try {
			if(args.length == 0) {
				System.out.println("input layout file name");
				return;
			}
			String[] layoutFileName = new String[6];
			layoutFileName[0] = args[0].replace(".xml", "")+Util.SUFFIX_10_160;
			layoutFileName[1] = args[0].replace(".xml", "")+Util.SUFFIX_10_240;
			layoutFileName[2] = args[0].replace(".xml", "")+Util.SUFFIX_15_320;
			layoutFileName[3] = args[0].replace(".xml", "")+Util.SUFFIX_16_160;
			layoutFileName[4] = args[0].replace(".xml", "")+Util.SUFFIX_16_320;
			layoutFileName[5] = args[0].replace(".xml", "")+Util.SUFFIX_18_400;
			for(int i = 0;i<6;i++) {
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(layoutFileName[i])));
				while((result = br.readLine()) != null) {
					if(result.contains("px")) {
						String numString = result.replaceAll("[^0-9]", "");
						double num = Double.parseDouble(numString);
						double convertNum = 0.0;
						switch(i) {
						case 0:
							convertNum = num * 1.0;
							break;
						case 1:
							convertNum = num * (double)2 / (double)3;
							break;
						case 2:
							convertNum = num * 0.75;
							break;
						case 3:
							convertNum = num * 1.6;
							break;
						case 4:
							convertNum = num * 0.8;
							break;
						case 5:
							convertNum = num * 0.72;
							break;
						}
						convertNum = Math.round(convertNum*10.0)/10.0;
						int pos = result.indexOf(numString);
						String result2 = result.substring(0, pos)+String.valueOf(convertNum)+result.substring(pos+numString.length(), result.length());
						result = result2.replace("px", "dip");
						System.out.println(result);
					}
					bw.write(result + "\r\n");
					bw.flush();
				}
				br.close();
				bw.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
