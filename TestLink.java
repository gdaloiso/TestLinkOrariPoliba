import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class TestLink
{

    
    public static ArrayList<String> arrayCorsi = new ArrayList<>();
    public static ArrayList<String> arrayLink1 = new ArrayList<>();
    public static ArrayList<String> arrayLink2 = new ArrayList<>();
    public static int errors = 0;
    public static void main(String[] args)
    {
        TestLink test = new TestLink();
        test.getResouceFromAssetFile();
        for (int i=0; i< arrayCorsi.size(); i++)
        {
            boolean result1 = test.downloadFile(arrayLink1.get(i));
            if(!result1)
            {
				System.out.println("Link al corso "+ arrayCorsi.get(i) + "_I errato");
				errors = errors + 1;
            }
            /*boolean result2 = downloadFile(arrayLink2.get(i));
            if(!result2)
            {
				System.out.println("Link al corso "+ arrayCorsi.get(i) + "_II errato");
				errors = errors + 1;
            }*/
        }
        System.out.println("Finish with "+ errors + " errors");
        System.exit(errors);
        
    }
    
    
    public static void getResouceFromAssetFile()
	{
        BufferedReader reader = null;
        try
		{
            InputStream input = new FileInputStream(new File("link.txt"));
            reader = new BufferedReader( new InputStreamReader(input,"UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null)
			{
                String[] infoCorso = mLine.split(",");
                String mNome = infoCorso[0];
                String mLink1 = infoCorso[1];
                String mLink2 = infoCorso[2];

                //arrayCorsi.add(mNome.replaceAll("\\s",""));
                arrayCorsi.add(mNome);
                arrayLink1.add(mLink1.replaceAll("\\s",""));
                arrayLink2.add(mLink2.replaceAll("\\s",""));
            }

        }
		catch(IOException e)
		{
            e.printStackTrace();
        }
		finally
		{
            if (reader != null)
			{
                try
				{
                   reader.close();
                }
				catch (IOException e)
				{
                    e.printStackTrace()
                }

            }
        }
    }
    
    
    public static  boolean downloadFile(String fileUrl)
    {
       	try {
        	URL url = new URL(fileUrl);
        	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        	int code = urlConnection.getResponseCode();
			if(code==200)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
    }
}
