package mapper;

import java.util.List;
import java.util.Map;

public interface DataMapper {
  Map<? extends Object, ?> run(List<String> dataFromFile);
}
