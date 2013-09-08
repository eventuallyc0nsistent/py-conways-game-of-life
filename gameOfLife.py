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
		alivePositions = [[] for j in range (int(rows))]
		

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

# check the neighbors for cell
def getNeighbors(row,cell):

	# get x and y co-ordinate
	# add extra 1 for eliminating negatives
	x = row
	y = cell

	#create an empty list of 8 tuples
	neighbors = [[] for i in range (8)]

	# list for neighboring positions
	# [ [x-1,y-1]		[x-1,y] 		[x-1,y+1] 
	#   [x,y-1] 		[x,y] 			[x,y+1]
	# 	[x+1,y-1] 		[x+1,1] 		[x+1,y+1] ]
	
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

	print neighbors

lineNum = 0
for row in alivePositions :
	
	for cell in row :
		
		#print "row"+str(lineNum),cell
		getNeighbors(lineNum,cell)

	lineNum = lineNum+1