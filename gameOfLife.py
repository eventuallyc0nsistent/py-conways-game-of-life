lifeFile = open('life.txt','r')

i = 0 
# get line count
for line in lifeFile:

	# for first line with rows & cols count
	if (i == 0) :
		rowCols = line.split()
		rows = rowCols[0]
		cols = rowCols[1]

		# create empty list for each row
		alivePositions 		 = [[] for j in range (int(rows))]

		# create empty list of dead positions
		deadPositions 		 = [[] for j in range (int(rows))]

		# create empty padded row to remove non negatives
		# alivePositionsPadded = [[] for j in range (int(rows) + 1 )]
		

	# other lines
	else :
		
		# check in range
		if ( i < rows ):

			j = 0

			# for each character in line
			for j in range(j,len(line)):

				if(line[j] == '*') :

					# append position of alive cell
					alivePositions[i].append(j)

				else :
					deadPositions[i].append(j)

	# increment line count
	i = i + 1

# check the neighbors for cell
def getNeighbors(row,cell):

	# get x and y co-ordinate
	# add extra 1 for eliminating negatives
	x = row
	y = cell
	print x,y

	#create an empty list(neighbors) of 8 rows
	neighbors = [[] for i in range (8)]

	# list for neighboring positions
	# [ [x-1,y-1]		[x-1,y] 		[x-1,y+1] 
	#   [x,y-1] 		[x,y] 			[x,y+1]
	# 	[x+1,y-1] 		[x+1,y] 		[x+1,y+1] ]
	
	if (x-1 < 0) :
		x = x+1
	if (x+1 > 8):
		x = x-1

	if (y-1 < 0) :
		y = y+1
	if (y > 20):
		y = y-1

	#add to the corresponding row
	neighbors[x-1].append(y-1)
	neighbors[x-1].append(y)
	neighbors[x-1].append(y+1)

	neighbors[x].append(y-1)
	neighbors[x].append(y+1)

	neighbors[x+1].append(y-1)
	neighbors[x+1].append(y)
	neighbors[x+1].append(y+1)

	return neighbors

# get the list of neighbors that are alive
def getAliveNeighbors(neighborsList,alivePositionsList):
	
	# print "alive Positions : "+str(alivePositions)
	# print "neighbor Positions : "+str(neighborsList)
	
	aliveNeighbors = [ [] for i in range(8)]
	countNeighbors = 0

	for row in range (0,len(alivePositionsList)):
		
		# iterate through every cell in the alive Positions list
		for aliveCell in alivePositionsList[row]:
			
			# check for every cell in neighbors list in the same row
			for neighborCell in neighborsList[row]:
				
				if (aliveCell == neighborCell):

					# create a list of all the alive Neighboring cells or a cell
					aliveNeighbors[row].append(neighborCell)
					countNeighbors += 1
			
					print "alive neighbors list : "+str(aliveNeighbors)
			
			print "Count of Neighbors :"+str(countNeighbors)

			# if (len(aliveNeighbors) == 2 or len(aliveNeighbors) == 3) :
			# 	return aliveNeighbors
			# else :
			# 	aliveNeighbors = []
			# 	return aliveNeighbors
			
		


		

lineNum = 0
# print alivePositions

for row in alivePositions :
	
	for cell in row :
		
		neighborsList = getNeighbors(lineNum,cell)
		# print neighborsList
		aliveNeighbors = getAliveNeighbors(neighborsList,alivePositions)

	lineNum = lineNum+1