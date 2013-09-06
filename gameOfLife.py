lifeFile = open('life.txt','r')

i = 0 


# get line count
for line in lifeFile:

	# first line
	if (i == 0) :
		rowCols = line.split()
		rows = rowCols[0]
		cols = rowCols[1]
		starPositionList = [[]] * rows
		

	# other lines
	else :

		starPositionList[i] = []

		# check in range
		if ( i < rows ):

			j = 0

			# for each character in line
			for j in range(j,len(line)):

				if(line[j] == '*') :

					#list of positions of stars
					starPositionList[i].append(j)

	# increment line count
	i = i + 1

print starPositionList