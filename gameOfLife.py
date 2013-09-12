lifeFile = open('life.txt','r')

# variable declarations
i = 0 
replacedFile = ""
addSpace = "" 

# get line count
for line in lifeFile:


	# for first line with rows & cols count
	if (i == 0) :
		rowCols = line.split()
		rows = int(rowCols[0])
		cols = int(rowCols[1])

	else :
		
		# check in range
		if ( i < rows ):

			replacedLine1 = line.replace(" ","-")
			replacedLine2 = "-"+replacedLine1.replace("\n","-") #adding padding

			lineLength = len(replacedLine2)

			if(lineLength < cols):
				addSpace = '-'+('-'*(cols-lineLength))
			
			replacedLine = replacedLine2 + addSpace
			replacedFile = replacedFile + replacedLine + "\n"

	# increment line count
	i = i + 1

"""
	add extra lines with padding 
"""
addExtraLine = ""
addExtraLine = "-"*(cols+1) + "\n" 

""" 
	this is the final file with dashes and stars 
	adding extra two lines in the file

"""
replacedFile = addExtraLine  + replacedFile + addExtraLine + addExtraLine


"""
spliting the lines in the replacedFile to a list
"""
splitLines = replacedFile.splitlines()

"""
create a list of 
alivePositions : list of all the stars(*)
deadPositions  : list of all the dashes(-)
aliveNeighbors : list of all the neighbors that are alive
"""
alivePositions = [[] for j in range (len(splitLines))]
deadPositions  = [[] for j in range (len(splitLines))]

for i in range(len(splitLines)):

	for cells in range(len(splitLines[i])):

		if (splitLines[i][cells] == "*"):
			alivePositions[i].append(cells)

		if (splitLines[i][cells] == "-"):
			deadPositions[i].append(cells)


"""
	check the neighbors for cell (x,y)

	[x-1,y-1]		[x-1,y] 		[x-1,y+1] 
	 [x,y-1] 		 [x,y] 			 [x,y+1]
	[x+1,y-1] 		[x+1,y] 		[x+1,y+1]

"""
def getNeighbors(row,cell):

	# get x and y co-ordinate
	x = row
	y = cell

	#create an empty list(neighbors) of 8 rows
	neighbors = [[] for i in range (len(splitLines))]

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
	
	aliveNeighbors = [[] for j in range (len(splitLines))]

	#for same row
	for neighborCell in neighborsList[rowNum]:
		for aliveCell in alivePositionsList[rowNum]:
			if neighborCell == aliveCell :
				if aliveCell not in aliveNeighbors[rowNum]:
					aliveNeighbors[rowNum].append(aliveCell)

	#for prev row
	for neighborCell in neighborsList[rowNum-1]:
		for aliveCell in alivePositionsList[rowNum-1]:
			if neighborCell == aliveCell :
				if aliveCell not in aliveNeighbors[rowNum-1]:
					aliveNeighbors[rowNum-1].append(aliveCell)

	#for next row
	for neighborCell in neighborsList[rowNum+1]:
		for aliveCell in alivePositionsList[rowNum+1]:
			if neighborCell == aliveCell :
				if aliveCell not in aliveNeighbors[rowNum+1]:
					aliveNeighbors[rowNum+1].append(aliveCell)

	return aliveNeighbors

def isAlive(aliveNeighbors):
	count = 0 
	for row in aliveNeighbors:
		for cell in row :
			if(cell):
				count += 1

	if(count == 2 or count == 3):
		return 1
	else :
		return 0


"""
for every cell in the alivePositions list check aliveNeighbors
"""
lineNum = 0
nextGenList = [[] for j in range (len(splitLines))]

for row in alivePositions :
	
	for cell in row :
		
		# generate list of neighbors that will be for a cell
		neighborsList = getNeighbors(lineNum,cell)
		
		"""
		for the particular cell generate list of Neighbors that are alive
		by finding intersection between the neighbors it has 
		and the list of neighbors that are alive
		"""
		aliveNeighbors = getAliveNeighbors(neighborsList,alivePositions,lineNum)

		"""
		for each cell check if it will be alive
		a cell is alive if 2 or 3 neighbors are alive
		"""
		isAliveStatus = isAlive(aliveNeighbors)
		
		if(isAliveStatus):
			nextGenList[lineNum].append(cell)
		print nextGenList
	lineNum = lineNum+1