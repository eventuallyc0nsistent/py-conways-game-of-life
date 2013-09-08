lifeFile = open('life.txt','r')

i = 0 


# get line count
for line in lifeFile:

	# first line
	if (i == 0) :
		rowCols = line.split()
		rows = rowCols[0]
		cols = rowCols[1]
		alivePositions = [[]] * int(rows)
		

	# other lines
	else :
		
		alivePositions[i] = []

		# check in range
		if ( i < rows ):

			j = 0

			# for each character in line
			for j in range(j,len(line)):

				if(line[j] == '*') :

					#list of positions of stars
					alivePositions[i].append(j)

	# increment line count
	i = i + 1

# check the neighbors for cell
def getNeighbors(row,cell):


	# get x and y co-ordinate
	x = row
	y = cell

	#create an empty list of 8 
	neighbors = [[] for i in range (8)]

	# list for [x,y]  position 	   [ [0,0] [0,1] [0,2] 
	#		    			    	 [1,0] [1,1] [1,2]
	# 					  	   	     [2,0] [2,1] [2,2] ]
	neighbors[0].append(x-1)
	neighbors[0].append(y-1)

	neighbors[1].append(x-1)
	neighbors[1].append(y)

	neighbors[2].append(x-1)
	neighbors[2].append(y+1)

	neighbors[3].append(x)
	neighbors[3].append(y-1)

	neighbors[4].append(x)
	neighbors[4].append(y+1)

	neighbors[5].append(x+1)
	neighbors[5].append(y-1)

	neighbors[6].append(x+1)
	neighbors[6].append(y)

	neighbors[7].append(x+1)
	neighbors[7].append(y+1)

	print 	neighbors

print alivePositions

i = 0
for row in alivePositions :
	
	for cell in row :
		
		#print "row"+str(i),cell
		getNeighbors(i,cell)

	i = i+1