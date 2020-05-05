package Factory;

import android.content.Context;
import Dao.Dao;
import Entity.RevenuTraffic;
import Metier.RevenuTrafficDAO;

public class DAOFactory extends AbstractDAOFactory{

	@Override
	public Dao<RevenuTraffic> getRevenuTraffic(Context context) {
		// TODO Auto-generated method stub
		return new RevenuTrafficDAO(context);
	}

}
