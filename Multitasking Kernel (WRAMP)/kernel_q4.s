#Multitasking Kernel - Kelvin David 1345360
#Question 04 - Multitasking with a single task
#==========================================
#The function of this program is to be able
#to run a single task using the Multitasking
#method of saving/loading the context every
#time slice

.global main
.text

main:
	addi	$5, $0, 0x4D		#set timer interrupt
	
	#Setting up Serial Task
	la	$1, serial_pcb		#load serial task pcb
	
	la	$2, serial_pcb		#link serial task to itself
	sw	$2, pcb_link($1)
	
	la	$2, serial_stack	#set $sp to top of stack
	sw	$2, pcb_sp($1)

	la	$2, serial_main		#setup serial task main
	sw	$2, pcb_ear($1)		#into ear

	sw 	$5, pcb_cctrl($1)	#set the $cctrl

	la	$1, serial_pcb		
	sw	$1, current_task($0)	#load serial to current task

	sw	$0, TimerAck($0)	#clear existing timer interrupts
	addui	$4, $0, 24		#set to 100 interrupts per second
	sw	$4, TimerLoad($0)	#load into the timer
	addui	$4, $0, 0x3		#set automatic reset + enable
	sw	$4, TimerControl($0)	#load it to start timer
	
	movsg	$2, $evec		#get default handler
	sw	$2, old_vector($0)	#save it into memory
	la	$2, handler		#load custom handler
	movgs	$evec, $2		#make it new default handler

	j 	load_context		#load current task
handler:
	movsg	$13, $estat		#get exception
	andi	$13, $13, 0xFFB0	#if we handle (timer inter)
	beqz	$13, handle_timer	#goto handler
	lw	$13, old_vector($0)	#else load old handler
	jr	$13			#goto old handler
handle_timer:
	sw	$0, TimerAck($0)	#acknowledge timer interrupt
	lw	$13, counter($0)	#load counter
	addui	$13, $13, 1		#increment counter by 1
	sw	$13, counter($0)	#re store counter
	
	lw	$13, taskSlice($0)	#load timeSlice
	subi	$13, $13, 1		#decrement it by 1
	sw	$13, taskSlice($0)	#re store timeSlice

	lw	$13, taskSlice($0)	#Task has finished it's turn
	beqz	$13, dispatcher		#(if 0) go to dispatcher
	
	rfe				#return from exception
dispatcher:
save_context:
	lw	$13, current_task($0)	#load current task

	#save the current context (ALL REGISTERS)
	sw	$1, pcb_reg1($13)
	sw	$2, pcb_reg2($13)
	sw	$3, pcb_reg3($13)
	sw	$4, pcb_reg4($13)
	sw	$5, pcb_reg5($13)
	sw	$6, pcb_reg6($13)
	sw	$7, pcb_reg7($13)
	sw	$8, pcb_reg8($13)
	sw	$9, pcb_reg9($13)
	sw	$10, pcb_reg10($13)
	sw	$11, pcb_reg11($13)
	sw	$12, pcb_reg12($13)
	sw	$sp, pcb_sp($13)
	sw	$ra, pcb_ra($13)

	movsg	$1, $ers		#load whatever is in $13
	sw	$1, pcb_reg13($13)	#save it into pcb
	
	movsg	$1, $ear		#load current location
	sw	$1, pcb_ear($13)	#save it into pcb
	
	movsg	$1, $cctrl		#load cctrl
	sw	$1, pcb_cctrl($13)	#save it into pcb
schedule:
	lw	$13, current_task($0)	#load current task
	lw	$13, pcb_link($13)	#get the link address
	sw	$13, current_task($0)	#make the link the new current
load_context:
	lw	$13, current_task($0)	#load current task
	
	lw	$1, pcb_reg13($13)	#load the ers from it's pcb
	movgs	$ers, $1
	
	lw	$1, pcb_ear($13)	#load it's old location
	movgs	$ear, $1		#(to start where it left off)

	lw	$1, pcb_cctrl($13)	#load $cctrl
	movgs	$cctrl, $1		#to for it's needed exceptions

	#load the current context (ALL REGISTERS)
	lw	$1, pcb_reg1($13)
	lw	$2, pcb_reg2($13)
	lw	$3, pcb_reg3($13)
	lw	$4, pcb_reg4($13)
	lw	$5, pcb_reg5($13)
	lw	$6, pcb_reg6($13)
	lw	$7, pcb_reg7($13)
	lw	$8, pcb_reg8($13)
	lw	$9, pcb_reg9($13)
	lw	$10, pcb_reg10($13)
	lw	$11, pcb_reg11($13)
	lw	$12, pcb_reg12($13)
	lw	$sp, pcb_sp($13)
	lw	$ra, pcb_ra($13)

timeSlice:
	addui	$13, $0, 2		#set timeslice to 2 ticks
	sw	$13, taskSlice($0)	#store it
	rfe				#return from exception

.equ	pcb_link,  0
.equ	pcb_reg1,  1 
.equ	pcb_reg2,  2
.equ	pcb_reg3,  3
.equ	pcb_reg4,  4
.equ	pcb_reg5,  5
.equ	pcb_reg6,  6 
.equ	pcb_reg7,  7
.equ	pcb_reg8,  8
.equ	pcb_reg9,  9
.equ	pcb_reg10, 10
.equ	pcb_reg11, 11
.equ	pcb_reg12, 12
.equ	pcb_reg13, 13
.equ	pcb_sp,    14
.equ	pcb_ra,    15
.equ	pcb_ear,   16
.equ	pcb_cctrl, 17

.equ	TimerControl,	0x72000
.equ	TimerLoad,	0x72001
.equ	TimerAck,	0x72003

.data
taskSlice:
	.word 0

.bss
old_vector:
	.word
serial_pcb:
	.space 18
	.space 200
serial_stack:

current_task:
	.word
