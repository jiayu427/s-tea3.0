package com.github.lmm.test;



import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.runner.RunWith;



@RunWith(JUnitBaseRunner.class)
public class BaiduSearch {

	public String base= "yes";
	public FirstFrame getFirstFrame(){
		return this.new FirstFrame();
	}
	public class FirstFrame{
		public String base="No";
		public SecondFrame getSecondFrame(){
			return this.new SecondFrame();
		}
		public class SecondFrame {
			public String base="YesOrNo";
			public String getBase(){
				return FirstFrame.this.base;
			}
		}
	}
	

}
 