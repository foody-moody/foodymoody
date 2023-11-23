import { useFeedDetail } from 'service/queries/feed';

type InitialInputValues = {
  locationValue: string;
  images: FeedImage[];
  reviewValue: string;
};

// export const useInitialValue = (feedId: string): InitialInputValues => {
//   const { data: feedDetailData } = useFeedDetail(feedId);

//   // useEffect(() => {
//   //   if (initialMenuItems) {
//   //     console.log('initialMenuItems', initialMenuItems);

//   //     setMenuItems(initialMenuItems);
//   //   }
//   // }, [initialMenuItems]);

//   return {
//     locationValue: feedDetailData?.location || '',
//     images: feedDetailData?.images || [
//       {
//         id: self.crypto.randomUUID(),
//         imageUrl: '1',
//         menu: {
//           name: '',
//           rating: 0,
//         },
//       },
//     ],
//     reviewValue: feedDetailData?.review || '',
//   };
// };
export const useInitialValue = (feedId: string): InitialInputValues => {
  const { data: feedDetailData } = useFeedDetail(feedId);

  if (feedDetailData) {
    // feedDetailData가 존재하는 경우
    return {
      locationValue: feedDetailData.location,
      images: feedDetailData.images,
      reviewValue: feedDetailData.review,
    };
  } else {
    // feedDetailData가 존재하지 않는 경우 빈 값 반환
    return {
      locationValue: '',
      images: [
        {
          id: self.crypto.randomUUID(),
          imageUrl: '1',
          menu: {
            name: '',
            rating: 0,
          },
        },
      ],
      reviewValue: '',
    };
  }
};
