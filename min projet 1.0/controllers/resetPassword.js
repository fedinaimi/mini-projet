const nodemailer = require("nodemailer");
module.exports=async(email,subject,text)=>{
    try{
        const transporter = nodemailer.createTransport({
            type: "SMTP",
            host: 'smtp.gmail.com',
            port: 465,
            secure: true,
            auth: {
                user: 'testini435@gmail.com',
                pass: 'zinehprkliupnwuk'
            }
        });
        await transporter.sendMail({
            from: 'CLUSTER <noreply@frippy.com>',
            to:email,
            subject:subject,
            text:text
        });
    }catch(error){
        console.log("Email not sent");
        console.log(error)

    }
}
