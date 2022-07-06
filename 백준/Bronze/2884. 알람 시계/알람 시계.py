timestr = input()
timelist = timestr.split(" ")

alarmtimelist = [int(timelist[0]), int(timelist[1]) - 45]
(hour, minute) = (alarmtimelist[0], alarmtimelist[1])
if minute < 0:
    minute += 60
    hour -= 1
if hour < 0:
    hour += 24

print(hour, minute)