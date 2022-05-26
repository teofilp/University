import {Card} from "antd";
import React from "react";

export const Information = () => {
    return (
        <Card
            id="info-card-style"
            className="card-style"
            hoverable
            title="Information"
        >
            <p>
                Starting with the 2020/2021 academic year, Babeș-Bolyai University
                provides its students with continuity of activity didactic, the online
                platform specific to the study activity Microsoft Teams and
                institutional email address. <br/>
                UBB as the operator of personal data took all measures to ensure the
                protection of your personal data in accordance with Regulation (EU)
                2016/679 para European Parliament and of the Council of 27 April 2016 on
                protection of individuals with regard to data processing personal data
                and on the free movement of such data (GDPR) <br/>
                For further details, please visit the section dedicated to the
                protection of personal data on the UBB website.
                https://www.ubbcluj.ro/ro/politici/ <br/>
                Thus, for the continuity of the teaching activity, both the staff both
                teachers and students are asked to use the platform online specific to
                the Microsoft Teams study activity. <br/>
                To access this platform you need the following data Login: Platform:
                https://portal.office.com
            </p>
        </Card>
    );
};
