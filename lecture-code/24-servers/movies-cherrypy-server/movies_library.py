class _movie_database:

       def __init__(self):
        self.movie_names = dict()
        self.movie_genres = dict()
        self.movie_ratings = dict() # maybe remove these

       def load_movies(self, movie_file):
        f = open(movie_file)
        for line in f:
                line = line.rstrip()
                components = line.split("::")
                mid = int(components[0])
                mname = components[1]
                mgenres = components[2]
                self.movie_names[mid] = mname
                self.movie_genres[mid] = mgenres
                self.movie_ratings[mid] = dict()
        f.close()

       def get_movies(self):
        return self.movie_names.keys()

       def get_movie(self, mid):
        try:
                mname = self.movie_names[mid]
                mgenres = self.movie_genres[mid]
                movie = list((mname, mgenres))
        except Exception as ex:
                movie = None
        return movie

       def set_movie(self, mid, movie):
        self.movie_names[mid] = movie[0]
        self.movie_genres[mid] = movie[1]

        if mid not in self.movie_ratings.keys():
                self.movie_ratings[mid] = dict()

       def delete_movie(self, mid):
        del(self.movie_names[mid])
        del(self.movie_genres[mid])
        del(self.movie_ratings[mid])


       def load_ratings(self, ratings_file):
        f = open(ratings_file)
        for line in f:
                line = line.rstrip()
                components = line.split("::")
                uid = int(components[0])
                mid = int(components[1])
                rating = int(components[2])
                self.movie_ratings[mid][uid] = rating
        f.close()

       def get_rating(self, mid):
        avg = 0
        total = 0
        mratings = self.movie_ratings[mid]
        for rating in mratings.values():
                avg += rating
                total += 1
        if total == 0:
                total = 1
        return (avg / float(total))

       def get_highest_rated_unvoted_movie(self, user_id):
           highest_user_rating = 0
           highest_rated_mid = 0
           for cur_mid in self.movie_names.keys():
             if cur_mid in self.movie_ratings and user_id in self.movie_ratings[cur_mid]:
               continue
             cur_avg_rating = self.get_rating(cur_mid)
             if (cur_avg_rating > highest_user_rating):
                highest_user_rating = cur_avg_rating
                highest_rated_mid = cur_mid
           return(highest_rated_mid)

       def get_highest_rated_movie(self):
        highest_user_rating = 0
        highest_rated_mid = 0
        for cur_mid in self.movie_names.keys():
                cur_avg_rating = self.get_rating(cur_mid)
                if (cur_avg_rating > highest_user_rating):
                        highest_user_rating = cur_avg_rating
                        highest_rated_mid = cur_mid
        return(highest_rated_mid)


       def delete_all_ratings(self):
        for mid in self.get_movies():
                self.movie_ratings[mid] = dict()

if __name__ == "__main__":
       mdb = _movie_database()

       #### MOVIES ########
       mdb.load_movies('movies.dat')

       movie = mdb.get_movie(2)
       print(movie[0])

       movie[0] = 'ABC'
       mdb.set_movie(2, movie)

       print("A")
       print(mdb.get_rating(51))
       print("B")

       movie = mdb.get_movie(2)
       print(movie[0])
       ####################

       #### RATINGS #######
       mdb.load_ratings('ratings.dat')

       rating = mdb.get_rating(1)
       print(rating)

       hrm_mid = mdb.get_highest_rated_movie()
       hrm_rating = mdb.get_rating(hrm_mid)
       hrm = mdb.get_movie(hrm_mid)
       hrm_name = hrm[0]
       print(hrm_mid, hrm_name, hrm_rating)
       ####################

