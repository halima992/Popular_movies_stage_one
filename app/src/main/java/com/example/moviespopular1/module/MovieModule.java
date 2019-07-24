package com.example.moviespopular1.module;


        public class MovieModule {
        private String title;
        private String poster;
        private String posterbig;
        private String vote;
        private String overview;
        private String release;
        private String DBTM_URL = "https://image.tmdb.org/t/p/w92/";
        private String DBTM_URL2 = "https://image.tmdb.org/t/p/w500/";


        public MovieModule(){}
        public MovieModule(String title , String poster,String vote,String overview,String release){
        this.title    = title;
        this.poster   = (DBTM_URL+poster);
        this.vote     = vote;
        this.overview = overview;
        this.release  = release;
        this.posterbig=(DBTM_URL2+poster);
        }

        //set

        public void setTitle(String title){
        this.title = title;
        }
        public void setPosterPath(String poster){
                this.poster = (DBTM_URL+poster);
        }

        public void setVote(String vote){
                this.vote = vote;
        }
        public void setOverview(String overview){
                this.overview = overview;
        }
        public void setRelease(String release){
                this.release = release;
        }

        //get
        public String getTitle(){
         return title;
        }
        public String getPosterPath(){
         return poster;}

        public String getOverview(){
                return overview;
        }
        public String getRelease(){
                return release;}
        public String getVote(){
                return vote;}
                public String getPosterbig(){return posterbig;}
        }
