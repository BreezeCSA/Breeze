package com.goku.breeze.ui.editor;
//import java.util.Scanner;

public class Mat { 
	/**         * @param args         */  
	public double[][] revMat(int n,double[][] vpp) {   
		// TODO Auto-generated method stub    
//		
//		 Scanner input = new Scanner( System.in);
		 
		
//		int nn=0;
		
//		System.out.println("������Ҫ��ľ���Ľ�����");
//		nn = n;
		
		double[][] Vp=new double[n][n];  
		
//		System.out.println("����Ҫ����ľ�����"+n+"�׾����밴����������Ԫ�أ�����Enter�����У�����Ҫ����"+n*n+"��������");
//		for( int row1 =0;row1 < n;row1++){
//			for( int column1 =0;column1 < n;column1++){
//				Vp[ row1 ][ column1 ] = input.nextDouble();
//			}
//			
//		}
		       
//		System.out.println("ԭ����Ϊ��");    
		Vp = vpp;
		Matrix.showMatrix(Vp);             
		double[][] rvs=Matrix.reverse(Vp);             
//		System.out.println("�����Ϊ��");            
		Matrix.showMatrix(rvs);             
//		System.out.println("ע�⣺���������ԭ������ͬ����˵���þ��󲻿���."); 
		return rvs;
		}                            
	}    //��װ���ھ�������ķ�����������ʾ������������    
class Matrix    
{        
	private Matrix(){}                    
	public static double[][] reverse(double[][] matrix)       
	{            
		double[][] temp;            
		double[][] back_temp;            
		//�õ�����Ľ���            
		int m_length=matrix.length;            
		//����n*��2n-1������ʽ�������������ԭ����͵�λ����          
		temp=new double[m_length][2*m_length];            
		//�������صľ���,��ʼ��        
		back_temp=matrix;            
		//��ԭ�����ֵ���� temp���󣬲���ӵ�λ�����ֵ           
		for(int x=0;x<m_length;x++)            
		{                
			for(int y=0;y<temp[0].length;y++)               
			{                    
				if(y>m_length-1)                  
				{                       
					if(x==(y-m_length))                           
						temp[x][y]=1;                        
					else                        
						temp[x][y]=0;                    
					}                    
				else                
				{                        
					temp[x][y]=matrix[x][y];                    
					}                
				}            
			}            
//		System.out.println("��Ͼ���:");            
		showMatrix(temp);            
		//��˹��Ԫ�������            
		for(int x=0;x<m_length;x++)            
		{                
			double var=temp[x][x];                
			//�ж϶Խ�����Ԫ���Ƿ�Ϊ0���ǵĻ��������н��н����У���û������������                
			//�����Ϊԭ����û�������Ȼ��ȡֵҪ��Ϊ0���е�ֵ                
			for(int w=x;w<temp[0].length;w++)                
			{                    
				if(temp[x][x]==0)                    
				{   
					int k;                        
					for(k=x+1;k<temp.length;k++)                        
					{                               
						if(temp[k][k]!=0)                            
						{                                
							for(int t=0;t<temp[0].length;t++)                                
							{   
								//System.out.println(">>>"+k+"<<<");                                    
								double tmp=temp[x][t];                                    
								temp[x][t]=temp[k][t];                                    
								temp[k][t]=tmp;                               
								}                            
							break;                            
							}                        
						}                        
					//System.out.println(""+k);                        
					//��������޷���temp�������߻�Ϊ��λ���󣬷���ԭ����                        
					     if(k>=temp.length) return back_temp;                        
					     var=temp[x][x];                        
//					     System.out.print("�� " + x + "�α任ǰ�滻��Ԫ�ϵ� 0");                        
//					     System.out.println("(��  " + x + " ����� " + k +" �н��н���)��");                        
					     showMatrix(temp);                    }                    
				temp[x][w] /=var;                }                
			//����x�е�Ԫ�س��Խ����ϵ�Ԫ���ⶼ��Ϊ0����������λ����                
			for(int z=0;z<m_length;z++)                
			{   double var_tmp=0.0;                    
			for(int w=x;w<temp[0].length;w++)                    
			{   
				//System.out.println("-"+x+"-"+z+"-"+w+"+++" + temp[z][w]);                         
				if(w==x) 
					var_tmp=temp[z][x];                    
				if(x!=z) temp[z][w] -=(var_tmp*temp[x][w]);                                                
				}                
			}                
//			System.out.println("�� " + x + "�α任:");                
			showMatrix(temp);            }            
		//ȡ������ֵ            
		for(int x=0;x<m_length;x++)            
		{                
			for(int y=0;y<m_length;y++)                
			{                    
				back_temp[x][y]=temp[x][y+m_length];                
				}               
			}            
		return back_temp;        
		}        
	public static void showMatrix(double[][] ma)        
	{            
		int x=ma.length;            
		int y=ma[0].length;                            
		for(int i=0;i<x;i++)            
		{                
			for(int j=0;j<y;j++)                    
				System.out.print("\t" + ma[i][j]);                
//			System.out.println();            
			}                        
		}            
	} 