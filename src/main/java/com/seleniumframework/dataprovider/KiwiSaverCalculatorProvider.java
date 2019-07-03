package com.seleniumframework.dataprovider;

import org.testng.annotations.DataProvider;

import com.seleniumframework.bean.EmployedBean;
import com.seleniumframework.bean.TwinBean;

public class KiwiSaverCalculatorProvider {
	
	  @DataProvider(name="kiwiSaverCalculatorProvider")
      public static Object[][] getDataFromDataprovider(){
          return new Object[][] {
              { new EmployedBean("30","Employed","82000","4","17.5%","High","$278,983")},
              { new TwinBean("45","Self-employed","10.5%","Medium","90","Fortnightly","290000","100000","$212,143")},
              { new TwinBean("55","Not employed","10.5%","Medium","10","Annually","200000","140000","$168,278")}
          };  
}

}
