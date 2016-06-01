import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class TestLink {
	private static Properties prop;
	private static final int MEGABYTE = 1024 * 1024;
	public static void main(String[] argv)
	{
		TestLink test = new TestLink();
		prop = readCredentials();
		Enumeration em = prop.keys();
  		while(em.hasMoreElements()){
  			String str = (String)em.nextElement();
			boolean result = test.downloadFile((String)prop.get(str));
			if(!result)
			{
				System.out.println("Link al corso "+ str + " errato");
			}
  		}
	}
	
	public static Properties readCredentials()
	{
		Properties prop = new Properties();
		try
		{
			InputStream input = new FileInputStream("config.properties");
			prop.load(input);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return prop;
	}
    public static  boolean downloadFile(String fileUrl) {
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int code = urlConnection.getResponseCode();
		    if(code==200){
                	return true;
            }else{
               		return false;
            }
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }
}
