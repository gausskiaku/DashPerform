package Factory;

import android.content.Context;
import Dao.Dao;

public abstract class AbstractDAOFactory {
	public abstract Dao getRevenuTraffic(Context context);
	
public static AbstractDAOFactory getFactory(FactoryType type){
		
		if(type.equals(type.DAO_FACTORY)) 
			return new DAOFactory();
		
		return null;
	}



}
