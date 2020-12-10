#Multitasking Kernel - Kelvin David 1345360
#Question 01 - Parallel Task
#==========================================
#The function of this program is to be able
#to print base16 and base10 to the SDD 
#dependant on the switch value

.global parallel_main
.text

parallel_main:
	subi	$sp, $sp, 1		#push stack
	sw	$ra, 0($sp)		#save $ra
loop:
	lw	$2, Switches($0)	#read switches
	lw 	$4, base($0)		#read base format
	
	sequi	$3, $4, 10		#if 10
	bnez	$3, write10		#write in base 10
write16:
	sw	$2, SSD_LR($0)		#write to LR SSD
	srli	$2, $2, 4		#read next 4 bits
	sw	$2, SSD_LL($0)		#write to LL SSD
	srli	$2, $2, 4		#read next 4 bits
	sw	$2, SSD_UR($0)		#write to UR SSD
	srli	$2, $2, 4		#read next 4 bits
	sw	$2, SSD_UL($0)		#write to UL SSD
	j 	bCheck			#check button pressed
write10:
	remu	$3, $2, $4		#get LSig digit
	divi	$2, $2, 10		#get next digit
	sw	$3, SSD_LR($0)		#print current digit

	remu	$3, $2, $4
	divi	$2, $2, 10
	sw	$3, SSD_LL($0)

	remu	$3, $2, $4		#repeat 4 times
	divi	$2, $2, 10
	sw	$3, SSD_UR($0)

	remu	$3, $2, $4
	divi	$2, $2, 10
	sw	$3, SSD_UL($0)
bCheck:
	lw	$4, buttons($0)		#Check if button's pressed
	beqz	$4, loop

	lw	$4, buttons($0)		#if button 0 do base16
	seqi	$4, $4, 0x1
	bnez	$4, base16
	
	lw 	$4, buttons($0)		#if button 1 do base10
	seqi	$4, $4, 0x2
	bnez	$4, base10
	
stop:					#else exit the program
	lw	$ra, 0($sp)		#reload $ra
	addui	$sp, $sp, 1		#pop stack

	jr 	$ra
base16:
	addui	$13, $0, 16		#change current format
	sw	$13, base($0)		#to base 16
	j	loop
base10:
	addui	$13, $0, 10		#change current format 
	sw	$13, base($0)		#to base 10
	j	loop		
.data
base:
	.word 16
end:
	.word 0

.equ	buttons,	0x73001
.equ	SSD_LR,		0x73003
.equ	SSD_LL, 	0x73002
.equ	SSD_UR,		0x73007
.equ	SSD_UL,		0x73006
.equ	Switches,	0x73000
