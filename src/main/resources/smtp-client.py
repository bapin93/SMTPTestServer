
# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.message import EmailMessage


msg = EmailMessage()

# me == the sender's email address
# you == the recipient's email address
msg['Subject'] = 'Subject'
msg['From'] = 'andres@pineda.ws'
msg['To'] = 'andres@pineda.ws'

# Send the message via our own SMTP server, but don't include the
# envelope header.
s = smtplib.SMTP('localhost')
s.send_message(msg)
s.quit()