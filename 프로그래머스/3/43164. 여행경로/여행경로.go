import ("sort")

type Ticket struct {
	src string
	dst string
}

func newTicketFrom(ticket []string) Ticket {
	return Ticket{
		src: ticket[0],
		dst: ticket[1],
	}
}

type void struct{}

func solution(tickets [][]string) []string {
	ticketLookupTable := make(map[string][]string)
	ticketMarkTable := make(map[Ticket]int)

	for _, ticketSlice := range tickets {
		ticket := newTicketFrom(ticketSlice)

		lookUpEntry, ok := ticketLookupTable[ticket.src]
		if !ok {
			ticketLookupTable[ticket.src] = []string{ticket.dst}
		} else {
			ticketLookupTable[ticket.src] = append(lookUpEntry, ticket.dst)
		}

		_, ok = ticketMarkTable[ticket]
		if !ok {
			ticketMarkTable[ticket] = 1
		} else {
			ticketMarkTable[ticket]++
		}
	}

	for _, dsts := range ticketLookupTable {
		sort.Slice(
			dsts,
			func(i, j int) bool {
				return dsts[i] < dsts[j]
			},
		)
	}

	result, _ := hopTicketsRecursively(
		ticketLookupTable,
		ticketMarkTable,
		"ICN",
		[]string{"ICN"},
	)

	return result
}

func hopTicketsRecursively(
	ticketLookupTable map[string][]string,
	ticketMarkTable map[Ticket]int,
	current string,
	traveled []string,
) ([]string, bool) {
	if allMarked(ticketMarkTable) {
		return traveled, true
	}

	nextDsts, _ := ticketLookupTable[current]
	for _, nextDst := range nextDsts {
		nextTicket := Ticket{src: current, dst: nextDst}
		if ticketMarkTable[nextTicket] != 0 {
			// forward
			ticketMarkTable[nextTicket]--

			result, finished := hopTicketsRecursively(
				ticketLookupTable,
				ticketMarkTable,
				nextDst,
				append(traveled, nextDst),
			)
			if finished {
				return result, finished
			}

			// revert
			ticketMarkTable[nextTicket]++
		}
	}

	return nil, false
}

func allMarked(table map[Ticket]int) bool {
	for _, mark := range table {
		if mark != 0 {
			return false
		}
	}

	return true
}
