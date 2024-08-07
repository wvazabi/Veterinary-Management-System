PGDMP  7                    |            veterinary_ management    16.2    16.2 A    Q           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            R           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            S           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            T           1262    25466    veterinary_ management    DATABASE     �   CREATE DATABASE "veterinary_ management" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
 (   DROP DATABASE "veterinary_ management";
                postgres    false            �            1259    25569    animal    TABLE     �  CREATE TABLE public.animal (
    animal_id bigint NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_colour character varying(255) NOT NULL,
    animal_birth_date date NOT NULL,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    25568    animal_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animal_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.animal_animal_customer_id_seq;
       public          postgres    false    219            U           0    0    animal_animal_customer_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.animal_animal_customer_id_seq OWNED BY public.animal.animal_customer_id;
          public          postgres    false    218            �            1259    25567    animal_animal_id_seq    SEQUENCE     }   CREATE SEQUENCE public.animal_animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.animal_animal_id_seq;
       public          postgres    false    219            V           0    0    animal_animal_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.animal_animal_id_seq OWNED BY public.animal.animal_id;
          public          postgres    false    217            �            1259    25594    appointment    TABLE     �   CREATE TABLE public.appointment (
    appointment_id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    appointment_animal_id bigint,
    appointment_doctor_id bigint
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    25593    appointment_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.appointment_appointment_id_seq;
       public          postgres    false    223            W           0    0    appointment_appointment_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.appointment_appointment_id_seq OWNED BY public.appointment.appointment_id;
          public          postgres    false    222            �            1259    25601    available_date    TABLE     �   CREATE TABLE public.available_date (
    available_date_id bigint NOT NULL,
    available_date date NOT NULL,
    available_date_doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    25600 $   available_date_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.available_date_available_date_id_seq;
       public          postgres    false    225            X           0    0 $   available_date_available_date_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.available_date_available_date_id_seq OWNED BY public.available_date.available_date_id;
          public          postgres    false    224            �            1259    25559    customer    TABLE     "  CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255),
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    25558    customer_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_customer_id_seq;
       public          postgres    false    216            Y           0    0    customer_customer_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;
          public          postgres    false    215            �            1259    25585    doctor    TABLE     6  CREATE TABLE public.doctor (
    doctor_id bigint NOT NULL,
    doctor_address character varying(255) NOT NULL,
    doctor_city character varying(255) NOT NULL,
    doctor_mail character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_phone character varying(11) NOT NULL
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    25584    doctor_doctor_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doctor_doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doctor_doctor_id_seq;
       public          postgres    false    221            Z           0    0    doctor_doctor_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doctor_doctor_id_seq OWNED BY public.doctor.doctor_id;
          public          postgres    false    220            �            1259    25608    report    TABLE     �   CREATE TABLE public.report (
    report_id bigint NOT NULL,
    diagnosis character varying(100) NOT NULL,
    price double precision NOT NULL,
    title character varying(100) NOT NULL,
    appointment_id bigint
);
    DROP TABLE public.report;
       public         heap    postgres    false            �            1259    25607    report_report_id_seq    SEQUENCE     }   CREATE SEQUENCE public.report_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.report_report_id_seq;
       public          postgres    false    227            [           0    0    report_report_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.report_report_id_seq OWNED BY public.report.report_id;
          public          postgres    false    226            �            1259    25615    vaccine    TABLE     ;  CREATE TABLE public.vaccine (
    vaccine_id bigint NOT NULL,
    vaccine_code character varying(255) NOT NULL,
    vaccine_name character varying(255) NOT NULL,
    vaccine_protection_finish_date date NOT NULL,
    vaccine_protection_start_date date NOT NULL,
    vaccine_animal_id bigint,
    report_id bigint
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    25614    vaccine_vaccine_id_seq    SEQUENCE        CREATE SEQUENCE public.vaccine_vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccine_vaccine_id_seq;
       public          postgres    false    229            \           0    0    vaccine_vaccine_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccine_vaccine_id_seq OWNED BY public.vaccine.vaccine_id;
          public          postgres    false    228            �           2604    25572    animal animal_id    DEFAULT     t   ALTER TABLE ONLY public.animal ALTER COLUMN animal_id SET DEFAULT nextval('public.animal_animal_id_seq'::regclass);
 ?   ALTER TABLE public.animal ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    219    217    219            �           2604    25573    animal animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animal ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animal_animal_customer_id_seq'::regclass);
 H   ALTER TABLE public.animal ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    25597    appointment appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointment ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointment_appointment_id_seq'::regclass);
 I   ALTER TABLE public.appointment ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    25604     available_date available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_date ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_date_available_date_id_seq'::regclass);
 O   ALTER TABLE public.available_date ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    224    225    225            �           2604    25562    customer customer_id    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    25588    doctor doctor_id    DEFAULT     t   ALTER TABLE ONLY public.doctor ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctor_doctor_id_seq'::regclass);
 ?   ALTER TABLE public.doctor ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    25611    report report_id    DEFAULT     t   ALTER TABLE ONLY public.report ALTER COLUMN report_id SET DEFAULT nextval('public.report_report_id_seq'::regclass);
 ?   ALTER TABLE public.report ALTER COLUMN report_id DROP DEFAULT;
       public          postgres    false    227    226    227            �           2604    25618    vaccine vaccine_id    DEFAULT     x   ALTER TABLE ONLY public.vaccine ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccine_vaccine_id_seq'::regclass);
 A   ALTER TABLE public.vaccine ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    228    229    229            D          0    25569    animal 
   TABLE DATA           �   COPY public.animal (animal_id, animal_breed, animal_colour, animal_birth_date, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    219   �P       H          0    25594    appointment 
   TABLE DATA           u   COPY public.appointment (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    223   �Q       J          0    25601    available_date 
   TABLE DATA           e   COPY public.available_date (available_date_id, available_date, available_date_doctor_id) FROM stdin;
    public          postgres    false    225   KR       A          0    25559    customer 
   TABLE DATA           ~   COPY public.customer (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    216   �R       F          0    25585    doctor 
   TABLE DATA           p   COPY public.doctor (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    221   �T       L          0    25608    report 
   TABLE DATA           T   COPY public.report (report_id, diagnosis, price, title, appointment_id) FROM stdin;
    public          postgres    false    227   kV       N          0    25615    vaccine 
   TABLE DATA           �   COPY public.vaccine (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_finish_date, vaccine_protection_start_date, vaccine_animal_id, report_id) FROM stdin;
    public          postgres    false    229   �V       ]           0    0    animal_animal_customer_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.animal_animal_customer_id_seq', 1, false);
          public          postgres    false    218            ^           0    0    animal_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animal_animal_id_seq', 13, true);
          public          postgres    false    217            _           0    0    appointment_appointment_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.appointment_appointment_id_seq', 10, true);
          public          postgres    false    222            `           0    0 $   available_date_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_date_available_date_id_seq', 15, true);
          public          postgres    false    224            a           0    0    customer_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customer_customer_id_seq', 16, true);
          public          postgres    false    215            b           0    0    doctor_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctor_doctor_id_seq', 39, true);
          public          postgres    false    220            c           0    0    report_report_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.report_report_id_seq', 2, true);
          public          postgres    false    226            d           0    0    vaccine_vaccine_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.vaccine_vaccine_id_seq', 13, true);
          public          postgres    false    228            �           2606    25577    animal animal_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animal_id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    219            �           2606    25599    appointment appointment_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    223            �           2606    25606 "   available_date available_date_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    225            �           2606    25566    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    216            �           2606    25592    doctor doctor_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (doctor_id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    221            �           2606    25613    report report_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (report_id);
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT report_pkey;
       public            postgres    false    227            �           2606    25624 #   report uk_n60tl23bf9da1jsuybplvrg99 
   CONSTRAINT     h   ALTER TABLE ONLY public.report
    ADD CONSTRAINT uk_n60tl23bf9da1jsuybplvrg99 UNIQUE (appointment_id);
 M   ALTER TABLE ONLY public.report DROP CONSTRAINT uk_n60tl23bf9da1jsuybplvrg99;
       public            postgres    false    227            �           2606    25622    vaccine vaccine_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (vaccine_id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    229            �           2606    25635 *   available_date fk3hhr5kyo13rc8g6u3p3bsyedu    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fk3hhr5kyo13rc8g6u3p3bsyedu FOREIGN KEY (available_date_doctor_id) REFERENCES public.doctor(doctor_id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fk3hhr5kyo13rc8g6u3p3bsyedu;
       public          postgres    false    3487    225    221            �           2606    25578 !   animal fk7v0huk1yclccxra61tk2wo16    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk7v0huk1yclccxra61tk2wo16 FOREIGN KEY (animal_customer_id) REFERENCES public.customer(customer_id);
 K   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk7v0huk1yclccxra61tk2wo16;
       public          postgres    false    219    216    3483            �           2606    25630 '   appointment fki81ox4a93tc9ka8c2fyd7p8h1    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fki81ox4a93tc9ka8c2fyd7p8h1 FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctor(doctor_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fki81ox4a93tc9ka8c2fyd7p8h1;
       public          postgres    false    223    3487    221            �           2606    25645 #   vaccine fko6uuy26nq7sv0pyruqjmwg43g    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fko6uuy26nq7sv0pyruqjmwg43g FOREIGN KEY (vaccine_animal_id) REFERENCES public.animal(animal_id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fko6uuy26nq7sv0pyruqjmwg43g;
       public          postgres    false    3485    229    219            �           2606    25640 "   report fkorag1ww0f2a059d8w1rkwb8j2    FK CONSTRAINT     �   ALTER TABLE ONLY public.report
    ADD CONSTRAINT fkorag1ww0f2a059d8w1rkwb8j2 FOREIGN KEY (appointment_id) REFERENCES public.appointment(appointment_id);
 L   ALTER TABLE ONLY public.report DROP CONSTRAINT fkorag1ww0f2a059d8w1rkwb8j2;
       public          postgres    false    3489    227    223            �           2606    25650 #   vaccine fkrh1k9yfb6avqkjy50ay0s0mjm    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkrh1k9yfb6avqkjy50ay0s0mjm FOREIGN KEY (report_id) REFERENCES public.report(report_id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkrh1k9yfb6avqkjy50ay0s0mjm;
       public          postgres    false    227    3493    229            �           2606    25625 '   appointment fksne2j50y78it7iyf8yjxhi1cl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fksne2j50y78it7iyf8yjxhi1cl FOREIGN KEY (appointment_animal_id) REFERENCES public.animal(animal_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fksne2j50y78it7iyf8yjxhi1cl;
       public          postgres    false    219    223    3485            D     x���Kn�0���SpW6˒>� 5J"U����ZO�B#N���^5T�YD�X����<Y4�������%�T���U��Y[�Q��yϯ�c���� �v���t�d�n ���6k��`a�=+���'�9~����*�i,M�=Z}@�=Z�<���@w�2�b����hM��Rm�',���^F�����!��Ec��Z��v��^�e����Nf��L&����B3X�3&}Xb����v�d�dp�TIg8b/7��,��      H   R   x�]��	�0E��)\ �4�u��C
b�p�7��t�܌'G��Q�e��H8&Յ�CUi�y�I����aHt\;��k�      J   \   x�U�A� Dѵs�.��9Z�F��} �EE[���2��&/�G���:�8��\�r?�<���j%���)8h)�R��V��/ ��&|      A   �  x��R�n�@>O��&��,8���n*5��؉�������b�B[�0��V�:��1�Q8�}��af$LkW)3\}�ހ���f�.5,�P�=�:�0�GAJߋ^Q��'��!(�CR�ŝb�H\3��F���mS�(�?���*�y	�i���M?��[����m��r[2��FUt}��T��+B0
e�/&�7���9d��mNM� G����	}G�-hg3m����<�����|��F���p�X�q�����h�8�%�{t��x�7� �K����)Ф�+���:�yˉ�VUgR�Ǉ��-�d2�{��h��	<Tw�Ks�=:q��p��LF/V!\)K�f�կ�6WДi�z�Y�Ö�������-�`�:?��-�D��豷�@�=�3�|���J���]RI�`�dŢ+�M�a��c��@]�}��'����9�2
eyA�?��D�7x�R�35��Iևޗ��y�04%�      F   �  x����n�0���S�-;Nn�Ӯ�Ӡ�a�]�IT�R ���X���=�$�I�v؉��?������	�͎5�Mg�+�`��1�����z:�3k-�l��e3��T�����چ��U�k/û(��N� ��ǀ��+$"��*���;�¹�Z��­eֽ1+�Z�C��A;�s[�3����4�űI���H�O�;���+>�f���d[����l�������Aj>/x�^˒1�6Ֆ�;Բ����mc􌴬I�d��_=��z�4f�1L�#�*�2=>��tp�I��z��'i��{P�Y
�.hn[F�N(�9���,�pi��	D�����~���MKz�)X���g�r�C�gP1��.s��<G'Q�_s߸�`��K
��V�v�7.�R�z��(�q�5�%p��e[�>��s?�=�������� ����.      L   6   x�3�I�;�Q�)3'=�8�����3(� �H�)����#��?��Ә+F��� wx+      N   �   x�3�t�	�s�51�t/�,PKLN��K�4202�54�54�2@L�?.KN�Ҕ�*�60��>S�>C�5�-�N��y%��0���E�y��z,t,�L3�^v����C�2����YjD��@�-�h6k6&O�1Hs� TUd     