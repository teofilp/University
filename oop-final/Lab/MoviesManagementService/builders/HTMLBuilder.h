//
// Created by Teodor Filp on 16/04/2021.
//

#pragma once
#ifndef A2_HTMLBUILDER_H
#define A2_HTMLBUILDER_H
#include <string>
#include <iostream>

#include "../ctml/ctml.hpp"

using std::string;
using CTML::Node;

class HTMLBuilder {
    private:
        CTML::Document doc;
        HTMLBuilder() {}

        void addTableBody(Node &table, vector<Movie> &movies) {
            std::cout << movies.size();
            Node tbody("body");
            for (auto &movie: movies) {
                Node tr("tr");
                tr.AppendChild(Node("td").AppendText(std::to_string(movie.id)));
                tr.AppendChild(Node("td").AppendText(movie.getTitle()));
                tr.AppendChild(Node("td").AppendText(movie.getGenre()));
                tr.AppendChild(Node("td").AppendChild(Node("a").AppendText("Link").SetAttribute("href", movie.getTrailerLink())));
                tr.AppendChild(Node("td").AppendText(std::to_string(movie.getReleaseYear())));
                tr.AppendChild(Node("td").AppendText(std::to_string(movie.getLikes())));
                tbody.AppendChild(tr);
            }
            table.AppendChild(tbody);
        }


        void addTableHeader(CTML::Node& table) {
            vector<string> columnNames { "Id", "Title", "Genre", "Trailer", "Release Year", "Likes" };
            Node thead("thead");
            Node tr("tr");

            for (auto &column: columnNames) {
                Node th("th");
                th.AppendText(column);
                tr.AppendChild(th);
            }

            thead.AppendChild(tr);
            table.AppendChild(thead);
        }

    public:
        static HTMLBuilder init() {
            return HTMLBuilder();
        }

    void addTableStyling() {
        Node style("style");
        style.AppendText("table, th, td { border: 2px solid black; border-collapse: collapse; text-align: center; padding: 4px 12px; }");
        doc.AppendNodeToHead(style);
    }

    string buildPage(vector<Movie> &movies) {
            Node table("table");
            addTableStyling();
            addTableHeader(table);
            addTableBody(table, movies);
            doc.AppendNodeToBody(table);
            return doc.ToString(CTML::ToStringOptions());
        }
};

#endif //A2_HTMLBUILDER_H
