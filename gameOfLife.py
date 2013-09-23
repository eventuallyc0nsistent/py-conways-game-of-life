lifeFile = open('life.txt','r')

# variable declarations
i = 0 
replacedFile = ""

# get line count
for line in lifeFile:


	# for first line with rows & cols count
	if (i == 0) :
		rowCols = line.split()
		rows = int(rowCols[0])
		cols = int(rowCols[1])

	else :
		
		# check in range
		if ( i <= rows ):

			replacedLine1 = line.replace(" ","-")
			replacedLine2 = replacedLine1.replace("\n","-")

			lineLength = len(replacedLine2)

			if(lineLength < cols):
				addSpace = ('-'*(cols-lineLength))
			else :
				addSpace = ""

			replacedLine = replacedLine2 + addSpace
			replacedFile = replacedFile + replacedLine + "\n"

	# increment line count
	i = i + 1

"""
add extra blank lines in the end
"""
splitLines = replacedFile.splitlines()

for i in range(len(splitLines),rows):
	extraLine = addSpace = ('-'*(cols))
	splitLines.append(extraLine)

"""
forming a GRID in 1 and 0
"""
grid = [[] for j in range (len(splitLines))]


# default value set for all cols
def getEmptyGrid():

	
	emptyGrid = [ [0]*cols for j in range (len(splitLines))]
	return emptyGrid



for i in range(len(splitLines)):

	for cells in range(len(splitLines[i])):

		if (splitLines[i][cells] == "*"):
			grid[i].append(1)

		if (splitLines[i][cells] == "-"):
			grid[i].append(0)

"""
	find if x,y is dead in a given list
"""
def isDead(x,y,listName):

	if (listName[x][y] == 0) :
		return True

	else :
		return False

"""
	check the neighbors for cell (x,y)

	[x-1,y-1]		[x-1,y] 		[x-1,y+1] 
	 [x,y-1] 		 [x,y] 			 [x,y+1]
	[x+1,y-1] 		[x+1,y] 		[x+1,y+1]

"""
def getNeighbors(rowNum,colNum,row):

	# get x and y co-ordinate
	x = rowNum
	y = colNum
	row = row
	realNeighbors = []
	neighbors=[]

	realNeighbors.append((x-1,y-1))
	realNeighbors.append((x-1,y))
	realNeighbors.append((x-1,y+1))

	realNeighbors.append((x,y-1))
	realNeighbors.append((x,y+1))

	realNeighbors.append((x+1,y-1))
	realNeighbors.append((x+1,y))
	realNeighbors.append((x+1,y+1))

	for tuple in realNeighbors :
		if(((tuple[0]>=0) and (tuple[0]<rows)) and ((tuple[1]>=0) and (tuple[1]<cols))):
			neighbors.append(tuple)

	return neighbors

"""
	given a grid and temp grid to store the result of the grid that will have alive cells
	-------------------------------------
	for each cell in row
	check if it is alive
	if yes then check neighbors
	and determine it will sustain for nextGen
	if yes then set that value in empty grid
"""
def willSustain(grid,emptyGrid):
	
	rowNum = 0

	for row in grid:

		colNum = 0

		for col in row :
			
			countOfNeighbors = 0

			IsDead = isDead(rowNum,colNum,grid)

			if(IsDead == False):
				neighbors = getNeighbors(rowNum,colNum,row)
				
				for tuple in neighbors:

					checkIsDead = isDead(tuple[0],tuple[1],grid)

					if (checkIsDead):
						pass
					else :
						countOfNeighbors += 1

			if(countOfNeighbors == 2 or countOfNeighbors == 3):
				
				emptyGrid[rowNum][colNum] = 1

			else :
				emptyGrid[rowNum][colNum] = 0

			colNum += 1
		rowNum += 1

	return emptyGrid

def willBloom(grid,emptyGrid):
	
	rowNum = 0

	for row in grid:

		colNum = 0

		for col in row :
			
			countOfNeighbors = 0

			IsDead = isDead(rowNum,colNum,grid)

			if(IsDead == True):

				neighbors = getNeighbors(rowNum,colNum,row)
				
				for tuple in neighbors:

					checkIsDead = isDead(tuple[0],tuple[1],grid)

					if (checkIsDead):
						pass
					else :
						countOfNeighbors += 1

				if(countOfNeighbors == 3):
					
					emptyGrid[rowNum][colNum] = 1

				else :
					pass 

			colNum += 1
		rowNum += 1

	return emptyGrid

"""
for 10 iterations
"""
for i in range(10):

	#next Gen for all alive cells
	emptyGrid = getEmptyGrid()
	oneLine = ''

	sustainGen = willSustain(grid,emptyGrid)
	nextGen = willBloom(grid,sustainGen)

	for row in nextGen:
		for cell in row :
			if cell == 0:
				oneLine = oneLine+'-'
			else :
				oneLine = oneLine+'*'
		
		oneLine = oneLine + "\n"
	
	oneLine = oneLine +"============================================="+"\n"

	grid = nextGen

	with open("out.txt", "a") as myfile:
    		myfile.write(oneLine)

	

