# Minterpreter

logic interpreter for Mindustry and Mindustack

## running preview

```
 * :logic to be run
------------------------------------------------------------------------------------<1>
0	 op add a0 a0 1
1	*set a1 a0
									|@counter:1.0
									|a0:1.0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<2>
0	 op add a0 a0 1
1	 set a1 a0
2	*jump start lessThan a1 5
									|a1:1.0
									|@counter:2.0
									|a0:1.0
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<3>
0	*op add a0 a0 1
									|a1:1.0
									|@counter:0.0
									|a0:1.0
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<4>
0	 op add a0 a0 1
1	*set a1 a0
									|a1:1.0
									|@counter:1.0
									|a0:2.0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<5>
0	 op add a0 a0 1
1	 set a1 a0
2	*jump start lessThan a1 5
									|a1:2.0
									|@counter:2.0
									|a0:2.0
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<6>
0	*op add a0 a0 1
									|a1:2.0
									|@counter:0.0
									|a0:2.0
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<7>
0	 op add a0 a0 1
1	*set a1 a0
									|a1:2.0
									|@counter:1.0
									|a0:3.0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<8>
0	 op add a0 a0 1
1	 set a1 a0
2	*jump start lessThan a1 5
									|a1:3.0
									|@counter:2.0
									|a0:3.0
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<9>
0	*op add a0 a0 1
									|a1:3.0
									|@counter:0.0
									|a0:3.0
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<10>
0	 op add a0 a0 1
1	*set a1 a0
									|a1:3.0
									|@counter:1.0
									|a0:4.0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<11>
0	 op add a0 a0 1
1	 set a1 a0
2	*jump start lessThan a1 5
									|a1:4.0
									|@counter:2.0
									|a0:4.0
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<12>
0	*op add a0 a0 1
									|a1:4.0
									|@counter:0.0
									|a0:4.0
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<13>
0	 op add a0 a0 1
1	*set a1 a0
									|a1:4.0
									|@counter:1.0
									|a0:5.0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<14>
0	 op add a0 a0 1
1	 set a1 a0
2	*jump start lessThan a1 5
									|a1:5.0
									|@counter:2.0
									|a0:5.0
3	 op sub a0 a0 a1
4	 stop

------------------------------------------------------------------------------------<15>
0	 op add a0 a0 1
1	 set a1 a0
2	 jump start lessThan a1 5
3	*op sub a0 a0 a1
									|a1:5.0
									|@counter:3.0
									|a0:5.0
4	 stop

------------------------------------------------------------------------------------<16>
0	 op add a0 a0 1
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	*stop
									|a1:5.0
									|@counter:4.0
									|a0:0.0

------------------------------------------------------------------------------------<17>
0	*op add a0 a0 1
									|a1:5.0
									|@counter:0.0
									|a0:0.0
1	 set a1 a0
2	 jump start lessThan a1 5
3	 op sub a0 a0 a1
4	 stop


```
