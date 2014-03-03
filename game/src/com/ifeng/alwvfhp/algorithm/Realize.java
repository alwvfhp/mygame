package com.ifeng.alwvfhp.algorithm;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Realize {
	public double V[][];
	private double S[][];
	private int[] Ku,Ko;
	private int[][] M;
	private int n,m;
	
	public Realize(String path){
		try(Scanner buf=new Scanner(
				new FileInputStream(path))){
			//初始化
			n=buf.nextInt();
			Ku=new int[n];
			m=buf.nextInt();
			Ko=new int[m];
			V=new double[n][m];
			S=new double[n][m];
			M=new int[n][m];
			Arrays.fill(Ku,0);
			Arrays.fill(Ko,0);
			//计算商品和人的度
			
			System.out.println(n+","+m);
			
			int i,j,k;
			for(i=0;i<n;i++){
				for(j=0;j<m;j++){
					M[i][j]=buf.nextInt();
					Ku[i]+=M[i][j];
					Ko[j]+=M[i][j];
				}
			}
			//计算S(ab)
			for(i=0;i<n;i++){
				for(j=0;j<n;j++){
					if(M[i][j]==1){
						S[i][j]=1;
						continue;
					}
					S[i][j]=0;
					for(k=0;k<m;k++){
						S[i][j]+=(M[i][k]==0 || M[j][k]==0)?0:1.0/Ko[k];
					}
					S[i][j]/=Ku[j];
				}
			}
			
			for(i=0;i<n;i++){
				double vd=0,vp=0;
				for(j=0;j<m;j++){
					if(M[i][j]==1){
						V[i][j]=0;
						continue;
					}
					for(k=0;k<n;k++){
						if(i==k)continue;
						vd+=S[i][k];
						vp+=S[i][k]*M[i][k];
					}
					V[i][j]=vp/vd;
				}
				System.out.println();
			}
			
			for(i=0;i<n;i++){
				for(j=0;j<m;j++){
					System.out.print("  "+V[i][j]);
				}
				System.out.println();
			}
			/**/
			
		} catch (Exception e) {
			System.out.println("文件格式错误");
			e.printStackTrace();
		}
	}
}
