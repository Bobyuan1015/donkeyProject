package yuancom.bob.myapplication.Modules;

/**
 * Created by bobyuan on 07/08/2017.
 */


public enum AddressType implements UrlValue {

    /** {@code STREET_ADDRESS} indicates a precise street address. */
    STREET_ADDRESS("street_address"),

    /** {@code ROUTE} indicates a named route (such as "US 101"). */
    ROUTE("route"),

    /** {@code INTERSECTION} indicates a major intersection, usually of two major roads. */
    INTERSECTION("intersection"),

    /**
     * {@code POLITICAL} indicates a political entity. Usually, this type indicates a polygon of some
     * civil administration.
     */
    POLITICAL("political"),

    /**
     * {@code COUNTRY} indicates the national political entity, and is typically the highest order
     * type returned by the Geocoder.
     */
    COUNTRY("country"),

    /**
     * {@code ADMINISTRATIVE_AREA_LEVEL_1} indicates a first-order civil entity below the country
     * level. Within the United States, these administrative levels are states. Not all nations
     * exhibit these administrative levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),

    /**
     * {@code ADMINISTRATIVE_AREA_LEVEL_2} indicates a second-order civil entity below the country
     * level. Within the United States, these administrative levels are counties. Not all nations
     * exhibit these administrative levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),

    /**
     * {@code ADMINISTRATIVE_AREA_LEVEL_3} indicates a third-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"),

    /**
     * {@code ADMINISTRATIVE_AREA_LEVEL_4} indicates a fourth-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_4("administrative_area_level_4"),

    /**
     * {@code ADMINISTRATIVE_AREA_LEVEL_5} indicates a fifth-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_5("administrative_area_level_5"),

    /** {@code COLLOQUIAL_AREA} indicates a commonly-used alternative name for the entity. */
    COLLOQUIAL_AREA("colloquial_area"),

    /** {@code LOCALITY} indicates an incorporated city or town political entity. */
    LOCALITY("locality"),

    /**
     * {@code WARD} indicates a specific type of Japanese locality, to facilitate distinction between
     * multiple locality components within a Japanese address.
     */
    WARD("ward"),

    /**
     * {@code SUBLOCALITY} indicates a first-order civil entity below a locality. For some locations
     * may receive one of the additional types: sublocality_level_1 to sublocality_level_5. Each
     * sublocality level is a civil entity. Larger numbers indicate a smaller geographic area.
     */
    SUBLOCALITY("sublocality"),
    SUBLOCALITY_LEVEL_1("sublocality_level_1"),
    SUBLOCALITY_LEVEL_2("sublocality_level_2"),
    SUBLOCALITY_LEVEL_3("sublocality_level_3"),
    SUBLOCALITY_LEVEL_4("sublocality_level_4"),
    SUBLOCALITY_LEVEL_5("sublocality_level_5"),

    /** {@code NEIGHBORHOOD} indicates a named neighborhood. */
    NEIGHBORHOOD("neighborhood"),

    /**
     * {@code PREMISE} indicates a named location, usually a building or collection of buildings with
     * a common name.
     */
    PREMISE("premise"),

    /**
     * {@code SUBPREMISE} indicates a first-order entity below a named location, usually a singular
     * building within a collection of buildings with a common name
     */
    SUBPREMISE("subpremise"),

    /**
     * {@code POSTAL_CODE} indicates a postal code as used to address postal mail within the country.
     */
    POSTAL_CODE("postal_code"),

    /**
     * {@code POSTAL_CODE_PREFIX} indicates a postal code prefix as used to address postal mail within
     * the country.
     */
    POSTAL_CODE_PREFIX("postal_code_prefix"),

    /** {@code NATURAL_FEATURE} indicates a prominent natural feature. */
    NATURAL_FEATURE("natural_feature"),

    /** {@code AIRPORT} indicates an airport. */
    AIRPORT("airport"),

    /** {@code UNIVERSITY} indicates a university. */
    UNIVERSITY("university"),

    /** {@code PARK} indicates a named park. */
    PARK("park"),

    /**
     * {@code POINT_OF_INTEREST} indicates a named point of interest. Typically, these "POI"s are
     * prominent local entities that don't easily fit in another category, such as "Empire State
     * Building" or "Statue of Liberty."
     */
    POINT_OF_INTEREST("point_of_interest"),

    /** {@code ESTABLISHMENT} typically indicates a place that has not yet been categorized. */
    ESTABLISHMENT("establishment"),

    /** {@code BUS_STATION} indicates the location of a bus stop. */
    BUS_STATION("bus_station"),

    /** {@code TRAIN_STATION} indicates the location of a train station. */
    TRAIN_STATION("train_station"),

    /** {@code SUBWAY_STATION} indicates the location of a subway station. */
    SUBWAY_STATION("subway_station"),

    /** {@code TRANSIT_STATION} indicates the location of a transit station. */
    TRANSIT_STATION("transit_station"),

    /** {@code LIGHT_RAIL_STATION} indicates the location of a light rail station. */
    LIGHT_RAIL_STATION("light_rail_station"),

    /** {@code CHURCH} indicates the location of a church. */
    CHURCH("church"),

    /** {@code FINANCE} indicates the location of a finance institute. */
    FINANCE("finance"),

    /** {@code POST_OFFICE} indicates the location of a post office. */
    POST_OFFICE("post_office"),

    /** {@code PLACE_OF_WORSHIP} indicates the location of a place of worship. */
    PLACE_OF_WORSHIP("place_of_worship"),

    /**
     * {@code POSTAL_TOWN} indicates a grouping of geographic areas, such as locality and sublocality,
     * used for mailing addresses in some countries.
     */
    POSTAL_TOWN("postal_town"),

    /** {@code SYNAGOGUE} is currently not a documented return type. */
    SYNAGOGUE("synagogue"),

    /** {@code FOOD} is currently not a documented return type. */
    FOOD("food"),

    /** {@code GROCERY_OR_SUPERMARKET} is currently not a documented return type. */
    GROCERY_OR_SUPERMARKET("grocery_or_supermarket"),

    /** {@code STORE} is currently not a documented return type. */
    STORE("store"),

    /** {@code LAWYER} is currently not a documented return type. */
    LAWYER("lawyer"),

    /** {@code HEALTH} is currently not a documented return type. */
    HEALTH("health"),

    /** {@code INSURANCE_AGENCY} is currently not a documented return type. */
    INSURANCE_AGENCY("insurance_agency"),

    /** {@code GAS_STATION} is currently not a documented return type. */
    GAS_STATION("gas_station"),

    /** {@code CAR_DEALER} is currently not a documented return type. */
    CAR_DEALER("car_dealer"),

    /** {@code CAR_REPAIR} is currently not a documented return type. */
    CAR_REPAIR("car_repair"),

    /** {@code MEAL_TAKEAWAY} is currently not a documented return type. */
    MEAL_TAKEAWAY("meal_takeaway"),

    /** {@code FURNITURE_STORE} is currently not a documented return type. */
    FURNITURE_STORE("furniture_store"),

    /** {@code HOME_GOODS_STORE} is currently not a documented return type. */
    HOME_GOODS_STORE("home_goods_store"),

    /** {@code SHOPPING_MALL} is currently not a documented return type. */
    SHOPPING_MALL("shopping_mall"),

    /** {@code GYM} is currently not a documented return type. */
    GYM("gym"),

    /** {@code ACCOUNTING} is currently not a documented return type. */
    ACCOUNTING("accounting"),

    /** {@code MOVING_COMPANY} is currently not a documented return type. */
    MOVING_COMPANY("moving_company"),

    /** {@code LODGING} is currently not a documented return type. */
    LODGING("lodging"),

    /** {@code STORAGE} is currently not a documented return type. */
    STORAGE("storage"),

    /**
     * Indicates an unknown address type returned by the server. The Java Client for Google Maps
     * Services should be updated to support the new value.
     */
    UNKNOWN("unknown");

    private final String addressType;

    AddressType(final String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return addressType;
    }

    public String toCanonicalLiteral() {
        return toString();
    }

    public String toUrlValue() {
        if (this == UNKNOWN) {
            throw new UnsupportedOperationException("Shouldn't use AddressType.UNKNOWN in a request.");
        }
        return addressType;
    }
}
