# Import smtplib for the actual sending function
import smtplib

# Import the email modules we'll need
from email.message import EmailMessage

msg = EmailMessage()

msg['Subject'] = 'Subject'
msg['From'] = 'test@test.com'
msg['To'] = 'test@test.com'
msg.set_content('This is the message body!')

# Send the message via our own SMTP server
s = smtplib.SMTP('localhost')
s.send_message(msg)
s.quit()