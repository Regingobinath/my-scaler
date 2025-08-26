package com.inventory.services;

import com.inventory.apiAdapter.MapApiAdapter;
import com.inventory.exceptions.AddressNotFoundException;
import com.inventory.exceptions.ProductNotFoundException;
import com.inventory.libraries.GoogleMapsApi;
import com.inventory.libraries.models.GLocation;
import com.inventory.models.Address;
import com.inventory.models.DeliveryHub;
import com.inventory.models.Product;
import com.inventory.models.Seller;
import com.inventory.repositories.AddressRepository;
import com.inventory.repositories.DeliveryHubRepository;
import com.inventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private AddressRepository addressRepository;
    private MapApiAdapter mapApiAdapter;
    private DeliveryHubRepository deliveryHubRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, AddressRepository addressRepository,
                              MapApiAdapter mapApiAdapter, DeliveryHubRepository deliveryHubRepository) {
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.mapApiAdapter = mapApiAdapter;
        this.deliveryHubRepository = deliveryHubRepository;
    }

    @Override
    @Transactional
    public Date estimateDeliveryDate(
            int productId, int addressId)
            throws ProductNotFoundException, AddressNotFoundException {
        Optional<Product> optionalproduct = productRepository.findById(productId);
        if(optionalproduct.isEmpty()){
            throw new ProductNotFoundException("Please enter valid product id");
        }
        Product product = optionalproduct.get();
        Seller seller = product.getSeller();
        Address selleAddress = seller.getAddress();
        GLocation sellerLocation= new GLocation();
        sellerLocation.setLatitude(selleAddress.getLatitude());
        sellerLocation.setLongitude(selleAddress.getLongitude());

        Optional<Address> optinalAddresss = addressRepository.findById(addressId);
        if(optinalAddresss.isEmpty()){
            throw new AddressNotFoundException("Please enter a valid addresss");
        }
        Address userAddress = optinalAddresss.get();

        GLocation userLocation= new GLocation();
        userLocation.setLatitude(userAddress.getLatitude());
        userLocation.setLongitude(userAddress.getLongitude());

        String zipCode = userAddress.getZipCode();
        Optional<DeliveryHub> optionalHub = deliveryHubRepository.findByAddress_ZipCode(zipCode);
        if(optionalHub.isEmpty()){
            throw new AddressNotFoundException("Please enter a valid addresss");
        }
        DeliveryHub deliveryHub = optionalHub.get();

        GLocation deliveryHubLocation=new GLocation();
        deliveryHubLocation.setLatitude(deliveryHub.getAddress().getLatitude());
        deliveryHubLocation.setLongitude(deliveryHub.getAddress().getLongitude());

        int hubTime = mapApiAdapter.estimate(sellerLocation, deliveryHubLocation);
        int userTime = mapApiAdapter.estimate(deliveryHubLocation, userLocation);
        int totalTime= hubTime + userTime;
        Date now= new Date();

        return new Date(now.getTime()+ totalTime * 1000L);
    }
}
