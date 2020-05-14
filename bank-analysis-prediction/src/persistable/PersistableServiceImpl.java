package persistable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistableServiceImpl implements PersistableService {

  @Override
  public List<String> getData(String filePath) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    String line = br.readLine();
    String[] arr = line.split("\\|");

    List<String> resultList = this.getDataFromFile(br);
    return resultList;
  }

  public List<String> getDataFromFile(BufferedReader br) throws IOException {

    int i;
    String finalString = "";
    List<String> linesList = new ArrayList<>();
    while ((i = br.read()) != -1) {
      finalString += Character.toString((char) i);
      if (i == '\n') {
        linesList.add(finalString);
        finalString = "";
      }
    }
    linesList.add(finalString);
    return linesList;
  }
}
