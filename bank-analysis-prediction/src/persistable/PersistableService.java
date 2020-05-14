package persistable;

import java.io.IOException;
import java.util.List;

public interface PersistableService {

  List<String> getData(String filePath) throws IOException;
}
