lifeFile = open('life.txt','r')

i = 0 

# get line count
for line in lifeFile:

	# first line
	if (i == 0) :
		rowCols = line.split()
		rows = rowCols[0]
		cols = rowCols[1]

	else :

		# check in range
		if ( i < rows ):
			j = 0

			# find occurences of *
			for j in range(j,len(line)):
				if(line[j] == '*') :
					print j

	# increment line count
	i = i + 1