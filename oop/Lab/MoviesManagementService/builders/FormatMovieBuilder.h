//
// Created by Teodor Filp on 20/03/2021.
//

#ifndef A2_FORMATMOVIEBUILDER_H
#define A2_FORMATMOVIEBUILDER_H

class FormatMovieBuilder {
private:
    string text;
    FormatMovieBuilder() {}
public:
    static FormatMovieBuilder init() {
        return FormatMovieBuilder();
    }

    FormatMovieBuilder withId(int id) {
        this->text += " | Id: " + std::to_string(id);
        return *this;
    }

    FormatMovieBuilder withTitle(string title) {
        this->text += " | Title: " + title;
        return *this;
    }

    FormatMovieBuilder withGenre(string genre) {
        this->text += " | Genre: " + genre;
        return *this;
    }

    FormatMovieBuilder withTrailerLink(string trailerLink) {
        this->text += " | Trailer: " + trailerLink;
        return *this;
    }

    FormatMovieBuilder withReleaseYear(int releaseYear) {
        this->text += " | Year: " + std::to_string(releaseYear);
        return *this;
    }

    FormatMovieBuilder withLikes(int likes) {
        this->text += " | Likes: " + std::to_string(likes);
        return *this;
    }

    string build() {
        this->text = this->text.substr(3, this->text.size());
        return this->text;
    }
};

#endif //A2_FORMATMOVIEBUILDER_H
