# Minterpreter
logic interpreter for Mindustry and Mindustack

## running preview
```
---------------------<11>
0  op add s d 1 
1  set d s 
2 *jump start LessThan d 5 
									s:4.0
									d:4.0
									@counter:2.0
3  set f 1 

---------------------<12>
0 *op add s d 1 
									s:4.0
									d:4.0
									@counter:0.0
1  set d s 
2  jump start LessThan d 5 
3  set f 1 

---------------------<13>
0  op add s d 1 
1 *set d s 
									s:5.0
									d:4.0
									@counter:1.0
2  jump start LessThan d 5 
3  set f 1 

---------------------<14>
0  op add s d 1 
1  set d s 
2 *jump start LessThan d 5 
									s:5.0
									d:5.0
									@counter:2.0
3  set f 1 

---------------------<15>
0  op add s d 1 
1  set d s 
2  jump start LessThan d 5 
3 *set f 1 
									s:5.0
									d:5.0
									@counter:3.0

---------------------<16>
0 *op add s d 1 
									s:5.0
									d:5.0
									f:1.0
									@counter:0.0
1  set d s 
2  jump start LessThan d 5 
3  set f 1 

---------------------<17>
0  op add s d 1 
1 *set d s 
									s:6.0
									d:5.0
									f:1.0
									@counter:1.0
2  jump start LessThan d 5 
3  set f 1 

```
