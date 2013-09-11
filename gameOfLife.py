lifeFile = open('life.txt','r')

i = 0 
# get line count
for line in lifeFile:

	# for first line with rows & cols count
	if (i == 0) :
		rowCols = line.split()
		rows = int(rowCols[0])
		cols = int(rowCols[1])

		# create empty list for each row
		alivePositions 		 = [[] for j in range (rows)]

		# create empty list of dead positions
		deadPositions 		 = [[] for j in range (rows)]

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


	# increment line count
	i = i + 1

aliveRowNum = 0

# create a list of dead Positions
rowNum = 0

# check the neighbors for cell
def getNeighbors(row,cell):

	# get x and y co-ordinate
	x = row
	y = cell
	print x,y

	#create an empty list(neighbors) of 8 rows
	neighbors = [[] for i in range (8)]

	# grid for neighboring positions
	# [ [x-1,y-1]		[x-1,y] 		[x-1,y+1] 
	#   [x,y-1] 		[x,y] 			[x,y+1]
	# 	[x+1,y-1] 		[x+1,y] 		[x+1,y+1] ]
	
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
def getAliveNeighbors(neighborsList,alivePositionsList,rowNum):
	
	print "neighbor Positions : "+str(neighborsList)
	print "alive Positions : "+str(alivePositions)
	print "row Number "+str(rowNum)


	aliveNeighbors = [ [] for i in range(len(alivePositionsList))]
	countNeighbors = 0 # keep the count of neighbors for a cell
	prevRow = 0
	nextRow = 0

	# check ALIVE neighbors in SAME row
	for aliveCell in alivePositionsList[rowNum] :
		
		if ((rowNum-1) < 0 ):
			prevRow = rowNum+1

		elif (rowNum+1 < rows ):
			nextRow = rowNum-1
		else :
			prevRow = rowNum
			nextRow = rowNum

		# check for same row
		for neighborCell in neighborsList[rowNum]:
			
			if aliveCell == neighborCell :
				aliveNeighbors[rowNum].append(aliveCell)

		print "alive Neighbors in same row"+str(aliveNeighbors)

	print "\n"




lineNum = 0
# print alivePositions

for row in alivePositions :
	
	for cell in row :
		
		# generate list of neighbors that will be for a cell
		neighborsList = getNeighbors(lineNum,cell)

		# for the particular cell generate list of Neighbors that are alive
		# by finding intersection between the neighbors it has 
		# and the list of neighbors that are alive
		
		aliveNeighbors = getAliveNeighbors(neighborsList,alivePositions,lineNum)

		# print aliveNeighbors


	lineNum = lineNum+1