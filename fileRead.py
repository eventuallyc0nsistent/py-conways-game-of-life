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
			replacedLine2 = replacedLine1.replace("\n","-")

			lineLength = len(replacedLine2)

			if(lineLength < cols):
				addSpace = '-'*(cols-lineLength)
			
			replacedLine = replacedLine2 + addSpace
			replacedFile = replacedFile + replacedLine + "\n"

	# increment line count
	i = i + 1

addExtraLine = ""
addExtraLine = "-"*cols + "\n"

""" 
	this is the final file with dashes and stars 
	adding extra two lines in the file

"""
replacedFile = addExtraLine  + replacedFile + addExtraLine + addExtraLine



"""
create a list of alivePositions & deadPositions
"""
alivePositions = [[] for j in range (rows)]
deadPositions  = [[] for j in range (rows)]


"""
spliting the lines in the replacedFile to a list
"""
splitLines = replacedFile.splitlines()

for i in range(len(splitLines)):
	for cells in range(len(splitLines[i])):
		
		if (splitLines[i][cells] == "*"):
			alivePositions[i].append(cells)

		if (splitLines[i][cells] == "-"):
			deadPositions[i].append(cells)

print alivePositions
print deadPositions