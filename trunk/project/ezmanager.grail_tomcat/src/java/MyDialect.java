import org.hibernate.dialect.Oracle10gDialect; 

public class MyDialect extends Oracle10gDialect { 
   public Class getNativeIdentifierGeneratorClass() { 
      return TableNameSequenceGenerator.class; 
   } 
} 
