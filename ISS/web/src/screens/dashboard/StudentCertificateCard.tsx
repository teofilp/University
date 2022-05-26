import {Card} from "antd";
import React from "react";

export const StudentCertificate = () => {
    return (
        <Card
            hoverable
            title={"Issuing Student Certificates"}
            id='certificate-card-style'
            className="card-style"
        >
            <p>
                For all students of the Faculty of Mathematics and Computer Science,
                starting with September 27, 2021, certificates will be issued
                student. The way to obtain the student certificate is
                next: The student completes all personal data and purpose
                requesting the issuance of the certificate on the form at:
                https://forms.gle/drqqY8FqjsJA2hh7A, then staff
                The secretary will issue the scanned certificate to the address of
                email indicated by the student. If a student has
                need more certificates, the form will be completed in May
                many times. If a student needs a student certificate in
                the original will specify this aspect in the form and will be able to pick it up
                from the Faculty Secretariat on any working day in the interval
                hours 9.00-12.00.
            </p>
        </Card>
    );
};