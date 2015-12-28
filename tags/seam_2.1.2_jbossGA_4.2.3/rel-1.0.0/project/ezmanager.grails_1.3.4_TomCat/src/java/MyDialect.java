import java.util.Properties; 

import org.hibernate.dialect.Dialect; 
import org.hibernate.dialect.Oracle10gDialect; 
import org.hibernate.id.PersistentIdentifierGenerator; 
import org.hibernate.id.SequenceGenerator; 
import org.hibernate.type.Type; 

public class MyDialect extends Oracle10gDialect { 
   public Class getNativeIdentifierGeneratorClass() { 
      return TableNameSequenceGenerator.class; 
   } 
} 
