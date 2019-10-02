****************************************************************************************

algs4.jar, StdRandom.java, StdStats.java, WeightedQuickUnionUF.java are provided

I just need to complete Percolation.java and PercolationStats.java

****************************************************************************************

--First Revision 90/100

I delete the codes that initalize the grid in Percolation() becauce its'elements was automaticly assigned to 0.

--Need improvement:

it wastes too much memory due to two WeightedQuickUnionUF are created.

***************************************************************************************

--Second Revision 90/100

I replaced all the checking valid index codes by a private method isValidInput() in Percolation.java.

ps. I still didn't get that why I can't get 100%.

***************************************************************************************

--Third Revision 92/100

In PercolationStatus.java, in constructor, I add a statement which detects whether the inputs n or trials are vaild.

ps. Thankfully it improves a little bit!
