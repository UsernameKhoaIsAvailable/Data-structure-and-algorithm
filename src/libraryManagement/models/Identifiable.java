package libraryManagement.models;

import java.io.Serializable;

public interface Identifiable extends Serializable {
    String getId();
    String getSearchableValue();
}
